package com.grinder.repository.queries;

import com.grinder.domain.entity.QImage;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class ImageQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ImageQueryRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public String findImageUrlByContentId(String contentId) {
        QImage image = QImage.image;

        String url = queryFactory
                .select(image.imageUrl)
                .from(image)
                .where(image.contentId.eq(contentId))
                .fetchOne();

        return url;
    }
}
