package com.grinder.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSellerInfo is a Querydsl query type for SellerInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSellerInfo extends EntityPathBase<SellerInfo> {

    private static final long serialVersionUID = 202342024L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSellerInfo sellerInfo = new QSellerInfo("sellerInfo");

    public final QCafe cafe;

    public final QMember member;

    public final NumberPath<Long> sellerInfoId = createNumber("sellerInfoId", Long.class);

    public QSellerInfo(String variable) {
        this(SellerInfo.class, forVariable(variable), INITS);
    }

    public QSellerInfo(Path<? extends SellerInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSellerInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSellerInfo(PathMetadata metadata, PathInits inits) {
        this(SellerInfo.class, metadata, inits);
    }

    public QSellerInfo(Class<? extends SellerInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cafe = inits.isInitialized("cafe") ? new QCafe(forProperty("cafe")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

