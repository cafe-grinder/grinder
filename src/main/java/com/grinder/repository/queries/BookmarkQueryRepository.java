package com.grinder.repository.queries;

import com.grinder.domain.dto.BookmarkDTO;
import com.grinder.domain.entity.QBookmark;
import com.grinder.domain.entity.QCafe;
import com.grinder.domain.entity.QImage;
import com.grinder.domain.entity.QMember;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookmarkQueryRepository {
    private final JPAQueryFactory queryFactory;

    public BookmarkQueryRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public Slice<BookmarkDTO.findAllResponse> findAllBookmarkSlice(String memberEmail, Pageable pageable) {
        QMember member = QMember.member;
        QCafe cafe = QCafe.cafe;
        QBookmark bookmark = QBookmark.bookmark;
        QImage image = QImage.image;

        long limit = pageable.getPageSize() + 1;
        long offset = pageable.getOffset();

        List<BookmarkDTO.findAllResponse> content = queryFactory
                .select(Projections.constructor(BookmarkDTO.findAllResponse.class,
                        bookmark,
                        image.imageUrl))
                .from(bookmark)
                .leftJoin(bookmark.member, member)
                .leftJoin(bookmark.cafe, cafe)
                .leftJoin(image).on(image.contentId.eq(cafe.cafeId))
                .where(member.email.eq(memberEmail))
                .limit(limit)
                .offset(offset)
                .fetch();

        boolean hasNext = content.size() > pageable.getPageSize();
        if (hasNext) {
            content.remove(content.size() - 1);
        }

        return new SliceImpl<>(content, pageable, hasNext);
    }

    public boolean existsByMemberEmailAndCafeId(String memberEmail, String cafeId) {
        QMember member = QMember.member;
        QCafe cafe = QCafe.cafe;
        QBookmark bookmark = QBookmark.bookmark;

        boolean exists = queryFactory
                .selectOne()
                .from(bookmark)
                .join(bookmark.member, member)
                .join(bookmark.cafe, cafe)
                .where(member.email.eq(memberEmail)
                        .and(cafe.cafeId.eq(cafeId)))
                .fetchFirst() != null;

        return exists;
    }
}
