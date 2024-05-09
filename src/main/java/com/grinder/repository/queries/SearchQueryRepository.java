package com.grinder.repository.queries;

import com.grinder.domain.dto.CafeDTO;
import com.grinder.domain.dto.FeedDTO;
import com.grinder.domain.dto.MemberDTO;
import com.grinder.domain.entity.*;
import com.grinder.domain.enums.ContentType;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SearchQueryRepository {
    private final JPAQueryFactory queryFactory;

    public SearchQueryRepository(EntityManager entityManager) {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    /**
     * TODO : 점수별로 feed 슬라이스(유저가 follow한 사람 중 최신 피드이면서 rank 기존 랭크에 +5,
     *                          feed 테이블의 rank가 높은 순에서 유저가 blacklist에 추가된 사람은 제외한 피드,
     *                          최근 2개월 내 게시물 중 rank가 높은 순은 기존 랭크에 +10
     *                          작성일 기준으로 7일로 나누어 나눈 값만큼 rank에서 뺄셈
     *                          이중 rank가 높은 순으로 슬라이스)
     */
    public Slice<FeedDTO.FeedAndImageResponseDTO> feedSuggestionSlice(String email, Pageable pageable) {
        LocalDateTime now = LocalDateTime.now();
        int pageSizePlusOne = pageable.getPageSize() + 1;

        QMember member = QMember.member;
        QFeed feed = QFeed.feed;
        QFollow follow = QFollow.follow;
        QBlacklist blacklist = QBlacklist.blacklist;
        QImage image = QImage.image;

        BooleanExpression isFollower = follow.following.memberId.eq(feed.member.memberId);
        NumberExpression<Integer> dynamicRank = feedRankBoost(feed.createdAt, now, feed.rank, isFollower);

        List<FeedDTO.FeedAndImageResponseDTO> results = queryFactory
                .select(feed)
                .from(feed)
                .join(feed.member, member)
                .leftJoin(follow).on(follow.following.memberId.eq(feed.member.memberId))
                .leftJoin(blacklist).on(blacklist.blockedMember.memberId.eq(feed.member.memberId))
                .where(follow.member.email.eq(email), blacklist.member.email.ne(email), blacklist.isNull())
                .orderBy(dynamicRank.desc())
                .offset(pageable.getOffset())
                .limit(pageSizePlusOne)
                .fetch()
                .stream()
                .map(arg -> {
                    List<String> images = queryFactory
                            .select(image.imageUrl)
                            .from(image)
                            .where(image.contentId.eq(arg.getFeedId()).and(image.contentType.eq(ContentType.FEED)))
                            .fetch();
                    return new FeedDTO.FeedAndImageResponseDTO(arg, images);
                })
                .collect(Collectors.toList());

        boolean hasNext = results.size() == pageSizePlusOne;
        if (hasNext) {
            results.remove(results.size() - 1);
        }

        return new SliceImpl<>(results, pageable, hasNext);
    }
    // feedSuggestionSlice 관련 메서드
    private NumberExpression<Integer> feedRankBoost(DateTimePath<LocalDateTime> createdAt, LocalDateTime now, NumberPath<Integer> baseRank, BooleanExpression isFollower) {
        // 날짜 차이 계산 (예: PostgreSQL의 date_part 함수 사용)
        NumberExpression<Integer> weeksPassed = Expressions.numberTemplate(Integer.class,
                "date_part('week', age({0}, {1}))", now, createdAt);

        // 최근 2달 이내인 피드에 대한 추가 점수
        NumberExpression<Integer> recentBonus = new CaseBuilder()
                .when(createdAt.after(now.minusMonths(2))).then(10)
                .otherwise(0);

        // 팔로우한 사용자의 피드에 대한 추가 점수
        NumberExpression<Integer> followerBonus = new CaseBuilder()
                .when(isFollower).then(50)
                .otherwise(0);

        // 최종 점수 계산: 기본 점수 + 최근 2달 보너스 + 팔로우 보너스 - 주차별 감소
        return baseRank.add(recentBonus).add(followerBonus).subtract(weeksPassed);
    }

    /**
     * TODO : 검색한 searchString 을 피드 내용에서 검색, 카페 이름이나 주소에서 검색, 회원 닉네임이나 email에서 검색
     */
    //피드 내용 검색(정확도순)
    public Slice<FeedDTO.FeedAndImageResponseDTO> searchFeedsByContentAccuracy(String searchString, Pageable pageable) {
        QFeed feed = QFeed.feed;
        QBlacklist blacklist = QBlacklist.blacklist;
        QImage image = QImage.image;

        long limit = pageable.getPageSize() + 1;
        long offset = pageable.getOffset();

        List<FeedDTO.FeedAndImageResponseDTO> list = queryFactory
                .select(feed)
                .from(feed)
                .leftJoin(blacklist).on(feed.member.memberId.eq(blacklist.blockedMember.memberId))
                .where(feed.content.containsIgnoreCase(searchString)
                        .and(blacklist.blacklistId.isNull()))
                .orderBy(feed.content.asc())
                .limit(limit)
                .offset(offset)
                .fetch()
                .stream()
                .map(arg -> {
                    List<String> images = queryFactory
                            .select(image.imageUrl)
                            .from(image)
                            .where(image.contentId.eq(arg.getFeedId()).and(image.contentType.eq(ContentType.FEED)))
                            .fetch();
                    return new FeedDTO.FeedAndImageResponseDTO(arg, images);
                })
                .collect(Collectors.toList());

        boolean hasNext = list.size() > pageable.getPageSize();
        if (hasNext) list.remove(list.size() - 1);

        return new SliceImpl<>(list, pageable, hasNext);
    }

    //피드 내용 검색(최신순)
    public Slice<FeedDTO.FeedAndImageResponseDTO> searchFeedsByContentRecent(String searchString, Pageable pageable) {
        QFeed feed = QFeed.feed;
        QBlacklist blacklist = QBlacklist.blacklist;
        QImage image = QImage.image;

        long limit = pageable.getPageSize() + 1;
        long offset = pageable.getOffset();

        List<FeedDTO.FeedAndImageResponseDTO> list = queryFactory
                .select(feed)
                .from(feed)
                .leftJoin(blacklist).on(feed.member.memberId.eq(blacklist.blockedMember.memberId))
                .where(feed.content.containsIgnoreCase(searchString)
                        .and(blacklist.blacklistId.isNull()))
                .orderBy(feed.createdAt.desc())
                .limit(limit)
                .offset(offset)
                .fetch()
                .stream()
                .map(arg -> {
                    List<String> images = queryFactory
                            .select(image.imageUrl)
                            .from(image)
                            .where(image.contentId.eq(arg.getFeedId()).and(image.contentType.eq(ContentType.FEED)))
                            .fetch();
                    return new FeedDTO.FeedAndImageResponseDTO(arg, images);
                })
                .collect(Collectors.toList());

        boolean hasNext = list.size() > pageable.getPageSize();
        if (hasNext) list.remove(list.size() - 1);

        return new SliceImpl<>(list, pageable, hasNext);
    }

    //피드 내용 검색(인기순)
    public Slice<FeedDTO.FeedAndImageResponseDTO> searchFeedsByContentPopular(String searchString, Pageable pageable) {
        QFeed feed = QFeed.feed;
        QBlacklist blacklist = QBlacklist.blacklist;
        QImage image = QImage.image;

        long limit = pageable.getPageSize() + 1;
        long offset = pageable.getOffset();

        List<FeedDTO.FeedAndImageResponseDTO> list = queryFactory
                .select(feed)
                .from(feed)
                .leftJoin(blacklist).on(feed.member.memberId.eq(blacklist.blockedMember.memberId))
                .where(feed.content.containsIgnoreCase(searchString)
                        .and(blacklist.blacklistId.isNull()))
                .orderBy(feed.hits.desc())
                .limit(limit)
                .offset(offset)
                .fetch()
                .stream()
                .map(arg -> {
                    List<String> images = queryFactory
                            .select(image.imageUrl)
                            .from(image)
                            .where(image.contentId.eq(arg.getFeedId()).and(image.contentType.eq(ContentType.FEED)))
                            .fetch();
                    return new FeedDTO.FeedAndImageResponseDTO(arg, images);
                })
                .collect(Collectors.toList());

        boolean hasNext = list.size() > pageable.getPageSize();
        if (hasNext) list.remove(list.size() - 1);

        return new SliceImpl<>(list, pageable, hasNext);
    }

    //카페 이름이나 주소 검색
    public List<CafeDTO.findAllWithImageResponse> searchCafesByNameOrAddress(String searchString) {
        QCafe cafe = QCafe.cafe;
        QImage image = QImage.image;

        return queryFactory
                .select(Projections.constructor(CafeDTO.findAllWithImageResponse.class, cafe, image.imageUrl))
                .from(cafe)
                .leftJoin(image).on(image.contentId.eq(cafe.cafeId))
                .where(cafe.name.containsIgnoreCase(searchString)
                        .or(cafe.address.containsIgnoreCase(searchString)))
                .fetch();
    }

    //회원 닉네임이나 이메일 검색
    public List<MemberDTO.FindMemberAndImageDTO> searchMembersByNicknameOrEmail(String searchString) {
        QMember member = QMember.member;
        QImage image = QImage.image;

        return queryFactory
                .select(Projections.constructor(MemberDTO.FindMemberAndImageDTO.class, member, image.imageUrl))
                .from(member)
                .leftJoin(image).on(image.contentId.eq(member.memberId))
                .where(member.nickname.containsIgnoreCase(searchString)
                        .or(member.email.containsIgnoreCase(searchString)))
                .fetch();
    }

    /**
     * TODO : 최근 일주일 간 피드에 가장 많이 참조된 카페 3곳
     */
    public List<CafeDTO.findAllWithImageResponse> findTop3CafesReferencedThisWeek() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfWeek = now.with(DayOfWeek.MONDAY).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfWeek = startOfWeek.plusWeeks(1).minusNanos(1);

        QCafe cafe = QCafe.cafe;
        QFeed feed = QFeed.feed;
        QImage image = QImage.image;

        List<CafeDTO.findAllWithImageResponse> topCafes = queryFactory
                .select(Projections.constructor(CafeDTO.findAllWithImageResponse.class, cafe, image.imageUrl))
                .from(feed)
                .join(feed.cafe, cafe)
                .leftJoin(image).on(image.contentId.eq(cafe.cafeId))
                .where(feed.createdAt.between(startOfWeek, endOfWeek))
                .groupBy(cafe.cafeId)
                .orderBy(cafe.count().desc())
                .limit(3)
                .fetch();

        return topCafes;
    }
}
