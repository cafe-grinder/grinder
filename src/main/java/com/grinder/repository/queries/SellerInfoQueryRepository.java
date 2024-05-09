package com.grinder.repository.queries;

import com.grinder.domain.dto.BookmarkDTO;
import com.grinder.domain.dto.SellerInfoDTO;
import com.grinder.domain.entity.QCafe;
import com.grinder.domain.entity.QImage;
import com.grinder.domain.entity.QMember;
import com.grinder.domain.entity.QSellerInfo;
import com.grinder.domain.enums.ContentType;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SellerInfoQueryRepository {
    private final JPAQueryFactory queryFactory;

    public SellerInfoQueryRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public List<SellerInfoDTO.findAllResponse> findAllSellerInfo(String memberEmail) {
        QMember member = QMember.member;
        QCafe cafe = QCafe.cafe;
        QSellerInfo sellerInfo = QSellerInfo.sellerInfo;
        QImage image = QImage.image;

        return queryFactory
                .select(Projections.constructor(SellerInfoDTO.findAllResponse.class,
                        sellerInfo,
                        image.imageUrl))
                .from(sellerInfo)
                .leftJoin(sellerInfo.member, member)
                .leftJoin(sellerInfo.cafe, cafe)
                .leftJoin(image).on(image.contentType.eq(ContentType.CAFE).and(image.contentId.eq(cafe.cafeId)))
                .where(member.email.eq(memberEmail))
                .fetch();
    }
}
