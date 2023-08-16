package xyz.iwasacar.api.domain.agreementhistories.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDocumentConsentHistory is a Querydsl query type for DocumentConsentHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDocumentConsentHistory extends EntityPathBase<DocumentConsentHistory> {

    private static final long serialVersionUID = -441433302L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDocumentConsentHistory documentConsentHistory = new QDocumentConsentHistory("documentConsentHistory");

    public final BooleanPath consent = createBoolean("consent");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final xyz.iwasacar.api.domain.documentitems.entity.QDocumentItem documentItem;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final xyz.iwasacar.api.domain.members.entity.QMember member;

    public final xyz.iwasacar.api.domain.products.entity.QProduct product;

    public final EnumPath<xyz.iwasacar.api.domain.common.constant.EntityStatus> status = createEnum("status", xyz.iwasacar.api.domain.common.constant.EntityStatus.class);

    public QDocumentConsentHistory(String variable) {
        this(DocumentConsentHistory.class, forVariable(variable), INITS);
    }

    public QDocumentConsentHistory(Path<? extends DocumentConsentHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDocumentConsentHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDocumentConsentHistory(PathMetadata metadata, PathInits inits) {
        this(DocumentConsentHistory.class, metadata, inits);
    }

    public QDocumentConsentHistory(Class<? extends DocumentConsentHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.documentItem = inits.isInitialized("documentItem") ? new xyz.iwasacar.api.domain.documentitems.entity.QDocumentItem(forProperty("documentItem"), inits.get("documentItem")) : null;
        this.member = inits.isInitialized("member") ? new xyz.iwasacar.api.domain.members.entity.QMember(forProperty("member")) : null;
        this.product = inits.isInitialized("product") ? new xyz.iwasacar.api.domain.products.entity.QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

