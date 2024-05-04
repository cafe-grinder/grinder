package com.grinder.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCafeSummary is a Querydsl query type for CafeSummary
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCafeSummary extends EntityPathBase<CafeSummary> {

    private static final long serialVersionUID = 1419097326L;

    public static final QCafeSummary cafeSummary = new QCafeSummary("cafeSummary");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath cafeId = createString("cafeId");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath summary = createString("summary");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QCafeSummary(String variable) {
        super(CafeSummary.class, forVariable(variable));
    }

    public QCafeSummary(Path<? extends CafeSummary> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCafeSummary(PathMetadata metadata) {
        super(CafeSummary.class, metadata);
    }

}

