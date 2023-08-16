package xyz.iwasacar.api.domain.caroptions.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCarOption is a Querydsl query type for CarOption
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCarOption extends EntityPathBase<CarOption> {

    private static final long serialVersionUID = -864789870L;

    public static final QCarOption carOption = new QCarOption("carOption");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath type = createString("type");

    public QCarOption(String variable) {
        super(CarOption.class, forVariable(variable));
    }

    public QCarOption(Path<? extends CarOption> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCarOption(PathMetadata metadata) {
        super(CarOption.class, metadata);
    }

}

