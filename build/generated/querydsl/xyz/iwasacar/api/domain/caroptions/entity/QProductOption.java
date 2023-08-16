package xyz.iwasacar.api.domain.caroptions.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductOption is a Querydsl query type for ProductOption
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductOption extends EntityPathBase<ProductOption> {

    private static final long serialVersionUID = 1753901805L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductOption productOption = new QProductOption("productOption");

    public final QCarOption carOption;

    public final QProductOption_Pk id;

    public final xyz.iwasacar.api.domain.products.entity.QProduct product;

    public QProductOption(String variable) {
        this(ProductOption.class, forVariable(variable), INITS);
    }

    public QProductOption(Path<? extends ProductOption> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductOption(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductOption(PathMetadata metadata, PathInits inits) {
        this(ProductOption.class, metadata, inits);
    }

    public QProductOption(Class<? extends ProductOption> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.carOption = inits.isInitialized("carOption") ? new QCarOption(forProperty("carOption")) : null;
        this.id = inits.isInitialized("id") ? new QProductOption_Pk(forProperty("id")) : null;
        this.product = inits.isInitialized("product") ? new xyz.iwasacar.api.domain.products.entity.QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

