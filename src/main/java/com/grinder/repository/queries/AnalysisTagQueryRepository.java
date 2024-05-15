package com.grinder.repository.queries;

import com.grinder.domain.entity.*;
import com.grinder.domain.enums.TagName;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class AnalysisTagQueryRepository {

    private final JPAQueryFactory queryFactory;

    public AnalysisTagQueryRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public List<String> AnalysisMemberTag(String email) {
        QBookmark bookmark = QBookmark.bookmark;
        QCafe cafe = QCafe.cafe;
        QTag tag = QTag.tag;
        QFeed feed = QFeed.feed;
        QHeart heart = QHeart.heart;

        List<TagName> topTags = queryFactory
                .select(tag.tagName)
                .from(tag)
                .leftJoin(tag.feed, feed) // 좋아요 누른 피드와 연결된 태그
                .leftJoin(heart).on(heart.contentId.eq(feed.feedId).and(heart.member.email.eq(email)))
                .leftJoin(cafe, feed.cafe) // 북마크한 카페와 연결된 태그
                .leftJoin(cafe).on(bookmark.cafe.eq(cafe))
                .leftJoin(bookmark).on(bookmark.cafe.eq(cafe).and(bookmark.member.email.eq(email)))
                .groupBy(tag.tagName)
                .orderBy(tag.count().sum().desc())
                .limit(5)
                .fetch();

        return topTags.stream().map(TagName::getValue).toList();
    }
}
