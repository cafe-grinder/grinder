package com.grinder.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCafe is a Querydsl query type for Cafe
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCafe extends EntityPathBase<Cafe> {

    private static final long serialVersionUID = 550850456L;

    public static final QCafe cafe = new QCafe("cafe");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath address = createString("address");

    public final NumberPath<Integer> averageGrade = createNumber("averageGrade", Integer.class);

    public final StringPath cafeId = createString("cafeId");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath name = createString("name");

    public final StringPath phoneNum = createString("phoneNum");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QCafe(String variable) {
        super(Cafe.class, forVariable(variable));
    }

    public QCafe(Path<? extends Cafe> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCafe(PathMetadata metadata) {
        super(Cafe.class, metadata);
    }

}

