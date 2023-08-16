package xyz.iwasacar.api.domain.documentitems.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDocumentItem is a Querydsl query type for DocumentItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDocumentItem extends EntityPathBase<DocumentItem> {

    private static final long serialVersionUID = 69441440L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDocumentItem documentItem = new QDocumentItem("documentItem");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final xyz.iwasacar.api.domain.documents.entity.QDocument document;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> order = createNumber("order", Integer.class);

    public final EnumPath<xyz.iwasacar.api.domain.common.constant.EntityStatus> status = createEnum("status", xyz.iwasacar.api.domain.common.constant.EntityStatus.class);

    public QDocumentItem(String variable) {
        this(DocumentItem.class, forVariable(variable), INITS);
    }

    public QDocumentItem(Path<? extends DocumentItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDocumentItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDocumentItem(PathMetadata metadata, PathInits inits) {
        this(DocumentItem.class, metadata, inits);
    }

    public QDocumentItem(Class<? extends DocumentItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.document = inits.isInitialized("document") ? new xyz.iwasacar.api.domain.documents.entity.QDocument(forProperty("document")) : null;
    }

}

