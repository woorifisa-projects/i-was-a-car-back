package xyz.iwasacar.api.domain.cartypes.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCarType is a Querydsl query type for CarType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCarType extends EntityPathBase<CarType> {

    private static final long serialVersionUID = -974413220L;

    public static final QCarType carType = new QCarType("carType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QCarType(String variable) {
        super(CarType.class, forVariable(variable));
    }

    public QCarType(Path<? extends CarType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCarType(PathMetadata metadata) {
        super(CarType.class, metadata);
    }

}

