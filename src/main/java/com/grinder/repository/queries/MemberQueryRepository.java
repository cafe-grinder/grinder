package com.grinder.repository.queries;

import static com.grinder.domain.dto.MemberDTO.*;

import com.grinder.domain.entity.QMember;
import com.grinder.domain.enums.Role;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public MemberQueryRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public Slice<FindMemberDTO> searchMemberByRoleAndNicknameSlice(String role, String nickname, Pageable pageable) {

        QMember member = QMember.member;

        BooleanBuilder builder = new BooleanBuilder();

        if (role != "") {
            builder.and(member.role.eq(Role.valueOf(role)));
        }

        if (nickname != null && nickname != "") {
            builder.and(member.nickname.like("%"+nickname+"%"));
        }

        long limit = pageable.getPageSize() + 1;
        long offset = pageable.getOffset();

        List<FindMemberDTO> content = queryFactory
                .select(Projections.constructor(FindMemberDTO.class, member))
                .from(member)
                .where(builder)
                .limit(limit)
                .offset(offset)
                .fetch();

        boolean hasNext = content.size() > pageable.getPageSize();
        if (hasNext) {
            content.remove(content.size() -1);
        }

        return new SliceImpl<>(content, pageable, hasNext);

    }

}
