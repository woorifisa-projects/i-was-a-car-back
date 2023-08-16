package xyz.iwasacar.api.domain.resources.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductImage is a Querydsl query type for ProductImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductImage extends EntityPathBase<ProductImage> {

    private static final long serialVersionUID = 1587141918L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductImage productImage = new QProductImage("productImage");

    public final QProductImage_Pk id;

    public final xyz.iwasacar.api.domain.products.entity.QProduct product;

    public final QResource resource;

    public final xyz.iwasacar.api.domain.roles.entity.QRole role;

    public QProductImage(String variable) {
        this(ProductImage.class, forVariable(variable), INITS);
    }

    public QProductImage(Path<? extends ProductImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductImage(PathMetadata metadata, PathInits inits) {
        this(ProductImage.class, metadata, inits);
    }

    public QProductImage(Class<? extends ProductImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QProductImage_Pk(forProperty("id")) : null;
        this.product = inits.isInitialized("product") ? new xyz.iwasacar.api.domain.products.entity.QProduct(forProperty("product"), inits.get("product")) : null;
        this.resource = inits.isInitialized("resource") ? new QResource(forProperty("resource")) : null;
        this.role = inits.isInitialized("role") ? new xyz.iwasacar.api.domain.roles.entity.QRole(forProperty("role")) : null;
    }

}

