package xyz.iwasacar.api.domain.products.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 769383422L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final NumberPath<Integer> accidentHistory = createNumber("accidentHistory", Integer.class);

    public final xyz.iwasacar.api.domain.brands.entity.QBrand brand;

    public final xyz.iwasacar.api.domain.colors.entity.QColor color;

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Double> displacement = createNumber("displacement", Double.class);

    public final NumberPath<Integer> distance = createNumber("distance", Integer.class);

    public final StringPath drivingMethod = createString("drivingMethod");

    public final BooleanPath fakeProductStatus = createBoolean("fakeProductStatus");

    public final StringPath fuel = createString("fuel");

    public final NumberPath<Double> fuelEfficiency = createNumber("fuelEfficiency", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath info = createString("info");

    public final BooleanPath inundationHistory = createBoolean("inundationHistory");

    public final xyz.iwasacar.api.domain.labels.entity.QLabel label;

    public final StringPath name = createString("name");

    public final xyz.iwasacar.api.domain.resources.entity.QResource performanceCheck;

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final EnumPath<xyz.iwasacar.api.domain.common.constant.EntityStatus> status = createEnum("status", xyz.iwasacar.api.domain.common.constant.EntityStatus.class);

    public final StringPath transmission = createString("transmission");

    public final DatePath<java.time.LocalDate> year = createDate("year", java.time.LocalDate.class);

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct(PathMetadata metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.brand = inits.isInitialized("brand") ? new xyz.iwasacar.api.domain.brands.entity.QBrand(forProperty("brand")) : null;
        this.color = inits.isInitialized("color") ? new xyz.iwasacar.api.domain.colors.entity.QColor(forProperty("color")) : null;
        this.label = inits.isInitialized("label") ? new xyz.iwasacar.api.domain.labels.entity.QLabel(forProperty("label")) : null;
        this.performanceCheck = inits.isInitialized("performanceCheck") ? new xyz.iwasacar.api.domain.resources.entity.QResource(forProperty("performanceCheck")) : null;
    }

}

