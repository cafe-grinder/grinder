package com.grinder.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMenu is a Querydsl query type for Menu
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenu extends EntityPathBase<Menu> {

    private static final long serialVersionUID = 551152474L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMenu menu = new QMenu("menu");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath allergy = createString("allergy");

    public final QCafe cafe;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath details = createString("details");

    public final BooleanPath isLimited = createBoolean("isLimited");

    public final StringPath menuId = createString("menuId");

    public final EnumPath<com.grinder.domain.enums.MenuType> menuType = createEnum("menuType", com.grinder.domain.enums.MenuType.class);

    public final StringPath name = createString("name");

    public final StringPath price = createString("price");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath volume = createString("volume");

    public QMenu(String variable) {
        this(Menu.class, forVariable(variable), INITS);
    }

    public QMenu(Path<? extends Menu> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMenu(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMenu(PathMetadata metadata, PathInits inits) {
        this(Menu.class, metadata, inits);
    }

    public QMenu(Class<? extends Menu> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cafe = inits.isInitialized("cafe") ? new QCafe(forProperty("cafe")) : null;
    }

}

