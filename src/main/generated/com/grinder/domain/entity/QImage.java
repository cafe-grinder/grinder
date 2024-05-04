package com.grinder.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QImage is a Querydsl query type for Image
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QImage extends EntityPathBase<Image> {

    private static final long serialVersionUID = -97611072L;

    public static final QImage image = new QImage("image");

    public final StringPath contentId = createString("contentId");

    public final EnumPath<com.grinder.domain.enums.ContentType> contentType = createEnum("contentType", com.grinder.domain.enums.ContentType.class);

    public final StringPath imageId = createString("imageId");

    public final StringPath imageUrl = createString("imageUrl");

    public QImage(String variable) {
        super(Image.class, forVariable(variable));
    }

    public QImage(Path<? extends Image> path) {
        super(path.getType(), path.getMetadata());
    }

    public QImage(PathMetadata metadata) {
        super(Image.class, metadata);
    }

}

