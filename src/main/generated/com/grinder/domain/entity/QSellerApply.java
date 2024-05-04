package com.grinder.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSellerApply is a Querydsl query type for SellerApply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSellerApply extends EntityPathBase<SellerApply> {

    private static final long serialVersionUID = 1970316500L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSellerApply sellerApply = new QSellerApply("sellerApply");

    public final StringPath applyId = createString("applyId");

    public final QCafe cafe;

    public final StringPath imageUrl = createString("imageUrl");

    public final QMember member;

    public QSellerApply(String variable) {
        this(SellerApply.class, forVariable(variable), INITS);
    }

    public QSellerApply(Path<? extends SellerApply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSellerApply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSellerApply(PathMetadata metadata, PathInits inits) {
        this(SellerApply.class, metadata, inits);
    }

    public QSellerApply(Class<? extends SellerApply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cafe = inits.isInitialized("cafe") ? new QCafe(forProperty("cafe")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

