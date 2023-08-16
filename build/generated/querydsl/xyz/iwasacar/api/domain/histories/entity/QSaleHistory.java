package xyz.iwasacar.api.domain.histories.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSaleHistory is a Querydsl query type for SaleHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSaleHistory extends EntityPathBase<SaleHistory> {

    private static final long serialVersionUID = 983254382L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSaleHistory saleHistory = new QSaleHistory("saleHistory");

    public final StringPath accountHolder = createString("accountHolder");

    public final StringPath accountNumber = createString("accountNumber");

    public final StringPath address = createString("address");

    public final StringPath addressDetail = createString("addressDetail");

    public final xyz.iwasacar.api.domain.banks.entity.QBank bank;

    public final DateTimePath<java.time.LocalDateTime> createAt = createDateTime("createAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> meetingSchedule = createDateTime("meetingSchedule", java.time.LocalDateTime.class);

    public final xyz.iwasacar.api.domain.members.entity.QMember member;

    public final xyz.iwasacar.api.domain.products.entity.QProduct product;

    public final EnumPath<xyz.iwasacar.api.domain.common.constant.EntityStatus> status = createEnum("status", xyz.iwasacar.api.domain.common.constant.EntityStatus.class);

    public final NumberPath<Integer> zipCode = createNumber("zipCode", Integer.class);

    public QSaleHistory(String variable) {
        this(SaleHistory.class, forVariable(variable), INITS);
    }

    public QSaleHistory(Path<? extends SaleHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSaleHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSaleHistory(PathMetadata metadata, PathInits inits) {
        this(SaleHistory.class, metadata, inits);
    }

    public QSaleHistory(Class<? extends SaleHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bank = inits.isInitialized("bank") ? new xyz.iwasacar.api.domain.banks.entity.QBank(forProperty("bank")) : null;
        this.member = inits.isInitialized("member") ? new xyz.iwasacar.api.domain.members.entity.QMember(forProperty("member")) : null;
        this.product = inits.isInitialized("product") ? new xyz.iwasacar.api.domain.products.entity.QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

