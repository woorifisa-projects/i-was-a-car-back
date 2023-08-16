package xyz.iwasacar.api.domain.loans.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLoan is a Querydsl query type for Loan
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLoan extends EntityPathBase<Loan> {

    private static final long serialVersionUID = -703643456L;

    public static final QLoan loan = new QLoan("loan");

    public final StringPath additionalFee = createString("additionalFee");

    public final StringPath content = createString("content");

    public final StringPath document = createString("document");

    public final StringPath earlyRedemptionCharge = createString("earlyRedemptionCharge");

    public final NumberPath<Integer> gracePeriod = createNumber("gracePeriod", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath interestChargedDay = createString("interestChargedDay");

    public final NumberPath<Integer> limit = createNumber("limit", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath overdueInterestRate = createString("overdueInterestRate");

    public final NumberPath<Integer> period = createNumber("period", Integer.class);

    public final StringPath redemption = createString("redemption");

    public final StringPath target = createString("target");

    public QLoan(String variable) {
        super(Loan.class, forVariable(variable));
    }

    public QLoan(Path<? extends Loan> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLoan(PathMetadata metadata) {
        super(Loan.class, metadata);
    }

}

