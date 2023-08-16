package xyz.iwasacar.api.domain.histories.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPurchaseHistory is a Querydsl query type for PurchaseHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPurchaseHistory extends EntityPathBase<PurchaseHistory> {

    private static final long serialVersionUID = 746300148L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPurchaseHistory purchaseHistory = new QPurchaseHistory("purchaseHistory");

    public final StringPath accountHolder = createString("accountHolder");

    public final StringPath address = createString("address");

    public final StringPath addressDetail = createString("addressDetail");

    public final xyz.iwasacar.api.domain.banks.entity.QBank bank;

    public final DateTimePath<java.time.LocalDateTime> createAt = createDateTime("createAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> deliverySchedule = createDateTime("deliverySchedule", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final xyz.iwasacar.api.domain.insurances.entity.QInsurance insurance;

    public final xyz.iwasacar.api.domain.loans.entity.QLoan loan;

    public final xyz.iwasacar.api.domain.members.entity.QMember member;

    public final xyz.iwasacar.api.domain.products.entity.QProduct product;

    public final EnumPath<xyz.iwasacar.api.domain.common.constant.EntityStatus> status = createEnum("status", xyz.iwasacar.api.domain.common.constant.EntityStatus.class);

    public final NumberPath<Integer> zipCode = createNumber("zipCode", Integer.class);

    public QPurchaseHistory(String variable) {
        this(PurchaseHistory.class, forVariable(variable), INITS);
    }

    public QPurchaseHistory(Path<? extends PurchaseHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPurchaseHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPurchaseHistory(PathMetadata metadata, PathInits inits) {
        this(PurchaseHistory.class, metadata, inits);
    }

    public QPurchaseHistory(Class<? extends PurchaseHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bank = inits.isInitialized("bank") ? new xyz.iwasacar.api.domain.banks.entity.QBank(forProperty("bank")) : null;
        this.insurance = inits.isInitialized("insurance") ? new xyz.iwasacar.api.domain.insurances.entity.QInsurance(forProperty("insurance")) : null;
        this.loan = inits.isInitialized("loan") ? new xyz.iwasacar.api.domain.loans.entity.QLoan(forProperty("loan")) : null;
        this.member = inits.isInitialized("member") ? new xyz.iwasacar.api.domain.members.entity.QMember(forProperty("member")) : null;
        this.product = inits.isInitialized("product") ? new xyz.iwasacar.api.domain.products.entity.QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

