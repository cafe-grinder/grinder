package com.grinder.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOpeningHours is a Querydsl query type for OpeningHours
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOpeningHours extends EntityPathBase<OpeningHours> {

    private static final long serialVersionUID = 80200594L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOpeningHours openingHours = new QOpeningHours("openingHours");

    public final QCafe cafe;

    public final TimePath<java.time.LocalTime> closeTime = createTime("closeTime", java.time.LocalTime.class);

    public final BooleanPath isHoliday = createBoolean("isHoliday");

    public final TimePath<java.time.LocalTime> openTime = createTime("openTime", java.time.LocalTime.class);

    public final EnumPath<com.grinder.domain.enums.Weekday> weekday = createEnum("weekday", com.grinder.domain.enums.Weekday.class);

    public QOpeningHours(String variable) {
        this(OpeningHours.class, forVariable(variable), INITS);
    }

    public QOpeningHours(Path<? extends OpeningHours> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOpeningHours(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOpeningHours(PathMetadata metadata, PathInits inits) {
        this(OpeningHours.class, metadata, inits);
    }

    public QOpeningHours(Class<? extends OpeningHours> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cafe = inits.isInitialized("cafe") ? new QCafe(forProperty("cafe")) : null;
    }

}

