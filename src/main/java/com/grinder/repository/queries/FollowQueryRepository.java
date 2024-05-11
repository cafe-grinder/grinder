package com.grinder.repository.queries;

import com.grinder.domain.dto.FollowDTO;
import com.grinder.domain.entity.QFollow;
import com.grinder.domain.entity.QImage;
import com.grinder.domain.entity.QMember;
import com.grinder.domain.enums.ContentType;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FollowQueryRepository {
    private final JPAQueryFactory queryFactory;

    public FollowQueryRepository(EntityManager entityManager) {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    public Slice<FollowDTO.findAllFollowingResponse> findAllFollowingSlice(String email, Pageable pageable) {
        QMember member = QMember.member;
        QFollow follow = QFollow.follow;
        QImage image = QImage.image;

        long limit = pageable.getPageSize() + 1;
        long offset = pageable.getOffset();

        List<FollowDTO.findAllFollowingResponse> content =
                queryFactory
                .select(Projections.constructor(FollowDTO.findAllFollowingResponse.class,
                                follow,
                                image.imageUrl))
                .from(follow)
                .join(follow.member, member)
                .leftJoin(image).on(image.contentType.eq(ContentType.MEMBER)
                        .and(image.contentId.eq(follow.member.memberId)))
                .where(member.email.eq(email).and(follow.following.isDeleted.isFalse()))
                .limit(limit)
                .offset(offset)
                .fetch();

        boolean hasNext = content.size() > pageable.getPageSize();
        if (hasNext) content.remove(content.size() - 1);

        return new SliceImpl<>(content, pageable, hasNext);
    }

    public Slice<FollowDTO.findAllFollowerResponse> findAllFollowerSlice(String email, Pageable pageable) {
        QMember member = QMember.member;
        QFollow follow = QFollow.follow;
        QImage image = QImage.image;

        long limit = pageable.getPageSize() + 1;
        long offset = pageable.getOffset();

        List<FollowDTO.findAllFollowerResponse> content = queryFactory
                .select(Projections.constructor(FollowDTO.findAllFollowerResponse.class,
                        follow,
                        image.imageUrl))
                .from(follow)
                .join(follow.following, member)
                .leftJoin(image).on(image.contentType.eq(ContentType.MEMBER)
                        .and(image.contentId.eq(follow.following.memberId)))
                .where(follow.following.email.eq(email).and(follow.member.isDeleted.isFalse()))
                .limit(limit)
                .offset(offset)
                .fetch();

        boolean hasNext = content.size() > pageable.getPageSize();
        if (hasNext) content.remove(content.size() - 1);

        return new SliceImpl<>(content, pageable, hasNext);
    }

    public boolean existsByMemberEmailAndFollowEmail(String email, String followEmail) {
        QMember memberFollower = new QMember("follower");
        QMember memberFollowing = new QMember("following");
        QFollow follow = QFollow.follow;

        boolean exists = queryFactory
                .selectOne()
                .from(follow)
                .join(follow.member, memberFollower)
                .join(follow.following, memberFollowing)
                .where(memberFollower.email.eq(email)
                        .and(memberFollowing.email.eq(followEmail)))
                .fetchFirst() != null;

        return exists;
    }
}
