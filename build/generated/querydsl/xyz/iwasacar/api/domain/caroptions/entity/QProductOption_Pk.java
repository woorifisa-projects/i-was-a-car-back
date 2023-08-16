package xyz.iwasacar.api.domain.caroptions.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductOption_Pk is a Querydsl query type for Pk
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QProductOption_Pk extends BeanPath<ProductOption.Pk> {

    private static final long serialVersionUID = -2083403588L;

    public static final QProductOption_Pk pk = new QProductOption_Pk("pk");

    public final NumberPath<Long> carOptionId = createNumber("carOptionId", Long.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public QProductOption_Pk(String variable) {
        super(ProductOption.Pk.class, forVariable(variable));
    }

    public QProductOption_Pk(Path<? extends ProductOption.Pk> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductOption_Pk(PathMetadata metadata) {
        super(ProductOption.Pk.class, metadata);
    }

}

