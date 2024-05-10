package com.grinder.repository.queries;

import com.grinder.domain.dto.CommentDTO;
import com.grinder.domain.dto.FeedDTO;
import com.grinder.domain.entity.*;
import com.grinder.domain.enums.ContentType;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


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

    public Slice<FeedDTO.FeedWithImageResponseDTO> findFeedWithImage(String email, Pageable pageable) {
        QFeed feed = QFeed.feed;
        QImage image = QImage.image;
        QTag tag = QTag.tag;
        QComment comment = QComment.comment;
        QHeart heart = QHeart.heart;
        QMember member = QMember.member;
        QCafe cafe = QCafe.cafe;

        List<FeedDTO.FeedWithImageResponseDTO> results = queryFactory.select(Projections.constructor(FeedDTO.FeedWithImageResponseDTO.class,
                        feed,
                        JPAExpressions.select(tag.tagName).from(tag).where(tag.feed.eq(feed)).distinct(),
                        JPAExpressions.select(Projections.constructor(CommentDTO.ParentCommentResponseDTO.class,
                                comment,
                                JPAExpressions.select(Projections.constructor(CommentDTO.ChildCommentResponseDTO.class, comment))
                                        .from(comment)
                                        .where(comment.parentComment.commentId.eq(comment.commentId)),
                                JPAExpressions.select(heart.count()).from(heart).where(heart.contentId.eq(comment.commentId), heart.contentType.eq(ContentType.COMMENT)),
                                JPAExpressions.selectFrom(heart).where(heart.contentId.eq(comment.commentId), heart.member.email.eq(email), heart.contentType.eq(ContentType.COMMENT)).exists()
                        )).from(comment).where(comment.feed.eq(feed), comment.parentComment.isNull()).distinct(),
                        JPAExpressions.select(image.imageUrl).from(image).where(image.contentId.eq(feed.feedId)).distinct(),
                        JPAExpressions.select(heart.count()).from(heart).where(heart.contentId.eq(feed.feedId), heart.contentType.eq(ContentType.FEED)),
                        JPAExpressions.selectFrom(heart).where(heart.contentId.eq(feed.feedId), heart.member.email.eq(email), heart.contentType.eq(ContentType.FEED)).exists()
                ))
                .from(feed)
                .leftJoin(feed.member, member)
                .leftJoin(feed.cafe, cafe)
                .where(feed.isVisible.isTrue())
                .orderBy(feed.updatedAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1) // 한 페이지 추가로 가져와서 다음 페이지 존재 여부 확인
                .fetch();

        boolean hasNext = results.size() > pageable.getPageSize();
        List<FeedDTO.FeedWithImageResponseDTO> content = hasNext ? results.subList(0, pageable.getPageSize()) : results;
        return new SliceImpl<>(content, pageable, hasNext);
    }

    List<Cafe> findRecentPopularCafeByFeedCount(int num) {
        QFeed feed = QFeed.feed;
        QCafe cafe = QCafe.cafe;

        List<Cafe> results = queryFactory
                .select(cafe)
                .from(cafe)
                .leftJoin(feed.cafe, cafe)
                .where(feed.createdAt.after(LocalDateTime.now().minusDays(60)))
                .groupBy(cafe.cafeId)
                .orderBy(feed.cafe.cafeId.count().desc())
                .limit(num)
                .fetch();

        return results;
    }
}
