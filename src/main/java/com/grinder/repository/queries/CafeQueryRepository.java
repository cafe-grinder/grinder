package com.grinder.repository.queries;

import com.grinder.domain.dto.CafeDTO;
import com.grinder.domain.dto.FeedDTO;
import com.grinder.domain.entity.*;
import com.grinder.domain.enums.ContentType;
import com.grinder.domain.enums.TagName;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CafeQueryRepository {

    private final JPAQueryFactory queryFactory;

    public CafeQueryRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public Slice<Cafe> searchCafeByNameAndAddressAndPhoneNum(String keyword, Pageable pageable) {
        QCafe cafe = QCafe.cafe;

        BooleanExpression expression = null;
        if (keyword != null && keyword != "") {
            expression = cafe.name.contains(keyword)
                    .or(cafe.address.contains(keyword))
                    .or(cafe.phoneNum.contains(keyword));
        }

        List<Cafe> cafeList = queryFactory
                .selectFrom(cafe)
                .where(expression)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = cafeList.size() > pageable.getPageSize();
        if (hasNext) {
            cafeList.remove(cafeList.size() - 1);
        }

        return new SliceImpl<>(cafeList, pageable, hasNext);
    }

    /**
     *
     * @param query : 파라미터에 해당하는 값
     * @return "query"에 해당하는 값을 찾고
     *          가장 많이 참조된 Tag2개와 카페 대표 이미지, 카페 정보를 반환합니다.
     */
    public Slice<CafeDTO.findAllWithImageAndTagResponse> searchCafes(String query, Pageable pageable) {
        QCafe cafe = QCafe.cafe;
        QImage image = QImage.image;
        QTag tag = QTag.tag;
        QFeed feed = QFeed.feed;

        List<CafeDTO.findAllWithImageResponse> list = queryFactory.select(Projections.constructor
                        (CafeDTO.findAllWithImageResponse.class, cafe, image.imageUrl))
                .from(cafe)
                .leftJoin(image).on(cafe.cafeId.eq(image.contentId))
                .where(cafe.name.containsIgnoreCase(query)
                        .or(cafe.address.containsIgnoreCase(query)))
                .orderBy(cafe.name.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        List<CafeDTO.findAllWithImageAndTagResponse> cafeList = list.stream().map(result -> {
            List<TagName> tagNames = queryFactory
                    .select(tag.tagName)
                    .from(tag)
                    .join(tag.feed, feed)
                    .where(feed.cafe.cafeId.eq(result.getCafeId()))
                    .groupBy(tag.tagName)
                    .orderBy(tag.count().desc())
                    .limit(2)
                    .fetch();

            return new CafeDTO.findAllWithImageAndTagResponse(result, tagNames);
        }).toList();

        boolean hasNext = cafeList.size() > pageable.getPageSize();
        List<CafeDTO.findAllWithImageAndTagResponse> content = hasNext ? cafeList.subList(0, pageable.getPageSize()) : cafeList;
        return new SliceImpl<>(content, pageable, hasNext);
    }

    /**
     * @return : 메인 페이지에서 사용될 일주내 가장 참조가 많이 된 카페 3개를 반환합니다.
     */
    public List<CafeDTO.findAllWithImageAndTagResponse> findTop3CafesReferencedThisWeek() {
        QCafe cafe = QCafe.cafe;
        QImage image = QImage.image;
        QTag tag = QTag.tag;
        QFeed feed = QFeed.feed;

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfWeek = now.with(DayOfWeek.MONDAY).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfWeek = startOfWeek.plusWeeks(1).minusNanos(1);

        List<CafeDTO.findAllWithImageResponse> list = queryFactory
                .select(cafe)
                .from(feed)
                .join(feed.cafe, cafe)
                .where(feed.createdAt.between(startOfWeek, endOfWeek))
                .groupBy(cafe.cafeId)
                .orderBy(cafe.cafeId.desc())
                .limit(3)
                .fetch()
                .stream().map(data -> {
                    String imageUrl = queryFactory
                            .select(image.imageUrl)
                            .from(image)
                            .leftJoin(image).on(image.contentId.eq(data.getCafeId()))
                            .where(image.contentType.eq(ContentType.CAFE), image.contentId.eq(data.getCafeId()))
                            .fetchFirst();

                    return new CafeDTO.findAllWithImageResponse(data, imageUrl);
                }).toList();

        List<CafeDTO.findAllWithImageAndTagResponse> cafeList = list.stream().map(result -> {
            List<TagName> tagNames = queryFactory
                    .select(tag.tagName)
                    .from(tag)
                    .join(tag.feed, feed)
                    .where(feed.cafe.cafeId.eq(result.getCafeId()))
                    .groupBy(tag.tagName)
                    .orderBy(tag.count().desc())
                    .limit(2)
                    .fetch();

            return new CafeDTO.findAllWithImageAndTagResponse(result, tagNames);
        }).toList();

        return cafeList;
    }

    /**
     * @return : 검색한 리스트에서 가장 많이 참조된 태그 2개를 반환합니다.
     */
    private List<CafeDTO.findAllWithImageAndTagResponse> findTopTag(List<CafeDTO.findAllWithImageResponse> list, QTag tag, QFeed feed) {
        return list.stream().map(result -> {
            List<TagName> tagNames = queryFactory
                    .select(tag.tagName)
                    .from(feed)
                    .join(tag.feed, feed)
                    .where(feed.cafe.cafeId.eq(result.getCafeId()))
                    .groupBy(tag.tagName)
                    .orderBy(tag.count().desc())
                    .limit(2)
                    .fetch();

            return new CafeDTO.findAllWithImageAndTagResponse(result, tagNames);
        }).toList();
    }
}
