package com.grinder.repository.queries;

import com.grinder.domain.entity.QMember;
import com.grinder.domain.entity.QMessage;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class MessageQueryRepository {
    private final JPAQueryFactory queryFactory;

    public MessageQueryRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public boolean existsNonCheck(String email) {
        QMessage message = QMessage.message;
        QMember member = QMember.member;

        return queryFactory.selectOne()
                .from(message)
                .join(message.receiveMember, member)
                .where(member.email.eq(email)
                        .and(message.isChecked.isFalse()))
                .fetchFirst() != null;
    }
}
