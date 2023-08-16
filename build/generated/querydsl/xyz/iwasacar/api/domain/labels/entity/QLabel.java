package xyz.iwasacar.api.domain.labels.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLabel is a Querydsl query type for Label
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLabel extends EntityPathBase<Label> {

    private static final long serialVersionUID = -219384L;

    public static final QLabel label = new QLabel("label");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<LabelName> name = createEnum("name", LabelName.class);

    public QLabel(String variable) {
        super(Label.class, forVariable(variable));
    }

    public QLabel(Path<? extends Label> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLabel(PathMetadata metadata) {
        super(Label.class, metadata);
    }

}

