package com.grinder.repository.queries;

import com.grinder.domain.dto.MenuDTO;
import com.grinder.domain.entity.QCafe;
import com.grinder.domain.entity.QImage;
import com.grinder.domain.entity.QMenu;
import com.grinder.domain.enums.ContentType;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuQueryRepository {
    private final JPAQueryFactory queryFactory;
    public MenuQueryRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }
    public List<MenuDTO.findAllMenuResponse> findAllMenuWithImage(String cafeId) {
        QCafe cafe = QCafe.cafe;
        QImage image = QImage.image;
        QMenu menu = QMenu.menu;

        List<MenuDTO.findAllMenuResponse> list = queryFactory
                .select(Projections.constructor(MenuDTO.findAllMenuResponse.class, menu, image.imageUrl))
                .from(menu)
                .leftJoin(menu.cafe, cafe)
                .leftJoin(image).on(image.contentType.eq(ContentType.MENU).and(image.contentId.eq(menu.menuId)))
                .where(menu.cafe.cafeId.eq(cafeId))
                .orderBy(menu.price.desc())
                .fetch();

        return list;
    }
}
