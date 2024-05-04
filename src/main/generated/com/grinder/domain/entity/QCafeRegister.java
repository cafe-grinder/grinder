package com.grinder.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCafeRegister is a Querydsl query type for CafeRegister
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCafeRegister extends EntityPathBase<CafeRegister> {

    private static final long serialVersionUID = 2104412763L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCafeRegister cafeRegister = new QCafeRegister("cafeRegister");

    public final StringPath address = createString("address");

    public final QMember member;

    public final StringPath name = createString("name");

    public final StringPath registerId = createString("registerId");

    public QCafeRegister(String variable) {
        this(CafeRegister.class, forVariable(variable), INITS);
    }

    public QCafeRegister(Path<? extends CafeRegister> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCafeRegister(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCafeRegister(PathMetadata metadata, PathInits inits) {
        this(CafeRegister.class, metadata, inits);
    }

    public QCafeRegister(Class<? extends CafeRegister> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

