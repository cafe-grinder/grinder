package com.grinder.repository.queries;

import com.grinder.domain.dto.BlacklistDTO;
import com.grinder.domain.entity.Blacklist;
import com.grinder.domain.entity.QBlacklist;
import com.grinder.domain.entity.QImage;
import com.grinder.domain.entity.QMember;
import com.grinder.domain.enums.ContentType;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BlacklistQueryRepository {
    private final JPAQueryFactory queryFactory;

    public BlacklistQueryRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public List<BlacklistDTO.findAllResponse> findAllBlacklistByMemberEmail(String memberEmail) {
        QMember member = QMember.member;
        QBlacklist blacklist = QBlacklist.blacklist;
        QImage image = QImage.image;

        List<Tuple> results = queryFactory
                .select(blacklist, image.imageUrl)
                .from(blacklist)
                .join(blacklist.member, member)
                .leftJoin(image).on(image.contentType.eq(ContentType.MEMBER)
                        .and(image.contentId.eq(blacklist.blockedMember.memberId)))
                .where(member.email.eq(memberEmail).and(blacklist.blockedMember.isDeleted.isFalse()))
                .fetch();

        return results.stream()
                .map(tuple -> {
                    Blacklist black = tuple.get(blacklist);
                    String imageUrl = tuple.get(image.imageUrl);
                    return new BlacklistDTO.findAllResponse(black,imageUrl);
                }).toList();
    }
}
