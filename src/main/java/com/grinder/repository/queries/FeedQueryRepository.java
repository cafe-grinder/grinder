package com.grinder.repository.queries;

import com.grinder.domain.dto.CommentDTO;
import com.grinder.domain.dto.FeedDTO;
import com.grinder.domain.entity.*;
import com.grinder.domain.enums.ContentType;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.querydsl.core.types.dsl.Expressions.cases;


@Repository
public class FeedQueryRepository {
    //TODO : 마이페이지 피드 쿼리(해당 멤버 작성 피드만), 카페 피드 쿼리(해당 카페 피드만)

    private final JPAQueryFactory queryFactory;

    public FeedQueryRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public Optional<FeedDTO.FindFeedDTO> findFeed(String feedId) {
        QFeed feed = QFeed.feed;
        QImage image = QImage.image;
        QTag tag = QTag.tag;

        FeedDTO.FindFeedDTO feedDTO = queryFactory
                .select(
                        Projections.constructor(FeedDTO.FindFeedDTO.class,
                                feed, feed.member, feed.cafe,
                                Projections.list(JPAExpressions
                                        .select(image.imageUrl)
                                        .from(image)
                                        .where(image.contentId.eq(feedId))), Projections.list(JPAExpressions
                                        .select(tag.tagName)
                                        .from(tag)
                                        .where(tag.feed.eq(feed)))))
                .from(feed)
                .where(feed.feedId.eq(feedId))
                .fetchOne();

        return Optional.ofNullable(feedDTO);
    }

    /**
     * @return
     */
    public Slice<FeedDTO.FeedWithImageResponseDTO> RecommendFeedWithImageForAno(Pageable pageable) {
        QFeed feed = QFeed.feed;
        QImage image = QImage.image;
        QHeart heart = QHeart.heart;
        QMember member = QMember.member;
        QCafe cafe = QCafe.cafe;

        List<Feed> feeds = queryFactory
                .selectFrom(feed)
                .leftJoin(feed.member, member)
                .leftJoin(feed.cafe, cafe)
                .where(feed.isVisible.isTrue())
                .orderBy(feed.updatedAt.desc())
                .offset(pageable.getOffset())
                .limit(4)
                .fetch();

        List<FeedDTO.FeedWithImageResponseDTO> content = feeds.stream().map(result -> {
            Long heartNum = queryFactory
                    .select(heart.count())
                    .from(heart)
                    .where(heart.contentType.eq(ContentType.FEED), heart.contentId.eq(result.getFeedId()))
                    .fetchOne();

            List<String> imageUrls = queryFactory
                    .select(image.imageUrl)
                    .from(image)
                    .where(image.contentType.eq(ContentType.FEED).and(image.contentId.eq(result.getFeedId())))
                    .fetch();

            return new FeedDTO.FeedWithImageResponseDTO(result, imageUrls, heartNum);
        }).toList();

        return new SliceImpl<>(content, pageable, false);
    }

    /**
     *
     * @param email : 접속 유저
     * @return : (조건)
     * - 내가 작성한 피드는 제외
     * - 내가 블랙리스트에 추가한 사람이 작성한 피드는 제외
     * 1. 내가 북마크한 카페에 동일하게 북마크를 한 사람이 좋아요를 누른 피드
     * 2. 내가 좋아요를 누른 피드에 동일하게 좋아요를 누른 사람이 좋아요를 누른 다른 피드
     * 3. 내가 북마크한 카페에 최근 피드 중 좋아요가 많이 눌린 피드
     *
     * rank 칼럼 순 (보류)
     */
    public Slice<FeedDTO.FeedWithImageResponseDTO> RecommendFeedWithImage(String email, Pageable pageable) {
        QFeed feed = QFeed.feed;
        QHeart heart = QHeart.heart;
        QComment comment = QComment.comment;
        QComment subComment = QComment.comment;
        QMember member = QMember.member;
        QBlacklist blacklist = QBlacklist.blacklist;
        QBookmark bookmark = QBookmark.bookmark;
        QCafe cafe = QCafe.cafe;
        QTag tag = QTag.tag;
        QImage image = QImage.image;

        return null;
    }

    /**
     * @param email : 접속한 유저
     * @return : 피드를 최신순으로 접속한 유저와 관련된 정보를 추출하여 보여줍니다.
     */
    public Slice<FeedDTO.FeedWithImageResponseDTO> findRecentFeedWithImage(String email, Pageable pageable) {
        QFeed feed = QFeed.feed;
        QImage image = QImage.image;
        QTag tag = QTag.tag;
        QComment comment = QComment.comment;
        QComment subComment = QComment.comment;
        QHeart heart = QHeart.heart;
        QMember member = QMember.member;
        QCafe cafe = QCafe.cafe;

        List<Feed> feeds = queryFactory
                .selectFrom(feed)
                .leftJoin(feed.member, member)
                .leftJoin(feed.cafe, cafe)
                .where(feed.isVisible.isTrue())
                .orderBy(feed.updatedAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        return FindCommentInfo(feeds,email,pageable,tag,image,comment,subComment,heart);
    }

    /**
     *
     * @param email : 접속한 유저
     * @param query : 검색 쿼리
     * @return : content에서 검색 쿼리에 맞는 조건을 찾은 후 최신 순으로 나열합니다.
     */
    public Slice<FeedDTO.FeedWithImageResponseDTO> findSearchRecentFeedWithImage(String email,String query, Pageable pageable) {
        QFeed feed = QFeed.feed;
        QImage image = QImage.image;
        QTag tag = QTag.tag;
        QComment comment = QComment.comment;
        QComment subComment = QComment.comment;
        QHeart heart = QHeart.heart;
        QMember member = QMember.member;
        QCafe cafe = QCafe.cafe;

        List<Feed> feeds = queryFactory
                .selectFrom(feed)
                .leftJoin(feed.member, member)
                .leftJoin(feed.cafe, cafe)
                .where(feed.isVisible.isTrue())
                .where(feed.content.containsIgnoreCase(query)
                        .or(feed.cafe.name.containsIgnoreCase(query))
                        .or(feed.member.nickname.containsIgnoreCase(query)))
                .orderBy(feed.updatedAt.desc(), feed.content.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        return FindCommentInfo(feeds,email,pageable,tag,image,comment,subComment,heart);
    }

    /**
     *
     * @param email : 접속한 유저
     * @param writerEmail : 유저정보페이지 이메일
     * @return : 유저 정보 페이지에서 해당 유저가 작성한 피드를 모아서 보여줍니다.
     */
    public Slice<FeedDTO.FeedWithImageResponseDTO> FindMemberFeedWithImage(String email, String writerEmail, Pageable pageable) {
        QFeed feed = QFeed.feed;
        QImage image = QImage.image;
        QTag tag = QTag.tag;
        QComment comment = QComment.comment;
        QComment subComment = QComment.comment;
        QHeart heart = QHeart.heart;
        QMember member = QMember.member;
        QCafe cafe = QCafe.cafe;

        // 먼저, 피드에 대한 기본 쿼리를 수행
        List<Feed> feeds = queryFactory
                .selectFrom(feed)
                .leftJoin(feed.member, member)
                .leftJoin(feed.cafe, cafe)
                .where(feed.isVisible.isTrue(), feed.member.email.eq(writerEmail))
                .orderBy(feed.updatedAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        return FindCommentInfo(feeds,email,pageable,tag,image,comment,subComment,heart);
    }

    /**
     *
     * @param email : 접속한 회원 email
     * @param cafeId : 확인하려는 cafeId
     * @return : 카페페이지에 접속 시 띄워줄 해당 카페 관련 피드를 호출합니다.
     */
    public Slice<FeedDTO.FeedWithImageResponseDTO> FindCafeFeedWithImage(String email, String cafeId, Pageable pageable) {
        QFeed feed = QFeed.feed;
        QImage image = QImage.image;
        QTag tag = QTag.tag;
        QComment comment = QComment.comment;
        QComment subComment = QComment.comment;
        QHeart heart = QHeart.heart;
        QMember member = QMember.member;
        QCafe cafe = QCafe.cafe;

        List<Feed> feeds = queryFactory
                .selectFrom(feed)
                .leftJoin(feed.member, member)
                .leftJoin(feed.cafe, cafe)
                .where(feed.isVisible.isTrue(), feed.cafe.cafeId.eq(cafeId))
                .orderBy(feed.updatedAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        return FindCommentInfo(feeds,email,pageable,tag,image,comment,subComment,heart);
    }

    /**
     * @param feeds : 검색이 완료된 feed List
     * @return : 태그 네임과, 이미지, 부모 댓글과 자식 댓글을 찾아 반환합니다.
     */
    private Slice<FeedDTO.FeedWithImageResponseDTO> FindCommentInfo(List<Feed> feeds, String email,Pageable pageable, QTag tag, QImage image, QComment comment, QComment subComment, QHeart heart) {
        List<FeedDTO.FeedWithImageResponseDTO> list = feeds.stream().map(result -> {
            List<Tag> tagNames = queryFactory
                    .select(tag)
                    .from(tag)
                    .where(tag.feed.eq(result))
                    .fetch();

            List<String> imageUrls = queryFactory
                    .select(image.imageUrl)
                    .from(image)
                    .where(image.contentType.eq(ContentType.FEED).and(image.contentId.eq(result.getFeedId())))
                    .fetch();

            List<CommentDTO.ParentCommentResponseDTO> parentComments = queryFactory
                    .selectFrom(comment)
                    .where(comment.feed.eq(result), comment.parentComment.isNull())
                    .fetch()
                    .stream()
                    .map(parent -> {
                        List<CommentDTO.ChildCommentResponseDTO> childComments = queryFactory
                                .selectFrom(subComment)
                                .where(subComment.parentComment.eq(parent))
                                .fetch()
                                .stream()
                                .map(chComment -> {
                                    // 좋아요 여부 확인
                                    Boolean isHeart = queryFactory
                                            .selectOne()
                                            .from(heart)
                                            .where(heart.contentType.eq(ContentType.COMMENT), heart.contentId.eq(chComment.getCommentId()),
                                                    heart.member.email.eq(email))  // 이메일 조건을 추가하여 특정 사용자가 좋아요를 눌렀는지 확인
                                            .fetchCount() > 0;

                                    // 좋아요 수 계산
                                    Long heartNum = queryFactory
                                            .select(heart.count())
                                            .from(heart)
                                            .where(heart.contentType.eq(ContentType.COMMENT), heart.contentId.eq(chComment.getCommentId()))
                                            .fetchOne();

                                    return new CommentDTO.ChildCommentResponseDTO(chComment, isHeart, heartNum);
                                })
                                .collect(Collectors.toList());

                        //부모 댓글 좋아요 여부 확인
                        Boolean isHeart = queryFactory.selectOne()
                                .from(heart)
                                .where(heart.contentType.eq(ContentType.COMMENT), heart.contentId.eq(parent.getCommentId()),
                                        heart.member.email.eq(email))
                                .fetchCount() > 0;
                        //댓글 좋아요 수 계산
                        Long heartNum = queryFactory.select(heart.count())
                                .from(heart)
                                .where(heart.contentType.eq(ContentType.COMMENT), heart.contentId.eq(parent.getCommentId()))
                                .fetchOne();
                        return new CommentDTO.ParentCommentResponseDTO(parent, childComments, isHeart, heartNum);
                    })
                    .collect(Collectors.toList());

            boolean isHeart = queryFactory
                    .selectFrom(heart)
                    .where(heart.contentType.eq(ContentType.FEED), heart.contentId.eq(result.getFeedId()), heart.member.email.eq(email))
                    .fetchCount() > 0;

            Long heartNum = queryFactory
                    .select(heart.count())
                    .from(heart)
                    .where(heart.contentType.eq(ContentType.FEED), heart.contentId.eq(result.getFeedId()))
                    .fetchOne();

            return new FeedDTO.FeedWithImageResponseDTO(result, tagNames, parentComments, imageUrls, isHeart, heartNum);
        }).collect(Collectors.toList());

        boolean hasNext = list.size() > pageable.getPageSize();
        List<FeedDTO.FeedWithImageResponseDTO> content = hasNext ? list.subList(0, pageable.getPageSize()) : list;
        return new SliceImpl<>(content, pageable, hasNext);
    }
}
