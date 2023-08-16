package xyz.iwasacar.api.domain.resources.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductImage_Pk is a Querydsl query type for Pk
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QProductImage_Pk extends BeanPath<ProductImage.Pk> {

    private static final long serialVersionUID = -750035733L;

    public static final QProductImage_Pk pk = new QProductImage_Pk("pk");

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final NumberPath<Long> resourceId = createNumber("resourceId", Long.class);

    public QProductImage_Pk(String variable) {
        super(ProductImage.Pk.class, forVariable(variable));
    }

    public QProductImage_Pk(Path<? extends ProductImage.Pk> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductImage_Pk(PathMetadata metadata) {
        super(ProductImage.Pk.class, metadata);
    }

}

