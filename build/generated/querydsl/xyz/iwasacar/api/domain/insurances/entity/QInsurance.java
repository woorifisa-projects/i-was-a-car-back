package xyz.iwasacar.api.domain.insurances.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QInsurance is a Querydsl query type for Insurance
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInsurance extends EntityPathBase<Insurance> {

    private static final long serialVersionUID = -1621547180L;

    public static final QInsurance insurance = new QInsurance("insurance");

    public final StringPath company = createString("company");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> monthlyPremium = createNumber("monthlyPremium", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> period = createNumber("period", Integer.class);

    public QInsurance(String variable) {
        super(Insurance.class, forVariable(variable));
    }

    public QInsurance(Path<? extends Insurance> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInsurance(PathMetadata metadata) {
        super(Insurance.class, metadata);
    }

}

