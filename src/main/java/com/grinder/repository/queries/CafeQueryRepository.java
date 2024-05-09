package com.grinder.repository.queries;

import com.grinder.domain.entity.Cafe;
import com.grinder.domain.entity.QCafe;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
