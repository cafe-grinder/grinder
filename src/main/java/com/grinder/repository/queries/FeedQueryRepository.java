package com.grinder.repository.queries;

import com.grinder.domain.dto.FeedDTO;
import com.grinder.domain.entity.*;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FeedQueryRepository {

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

}
