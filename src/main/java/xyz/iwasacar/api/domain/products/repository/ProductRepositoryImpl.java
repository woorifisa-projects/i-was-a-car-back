package xyz.iwasacar.api.domain.products.repository;

import java.util.Optional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.products.entity.Product;
import xyz.iwasacar.api.domain.products.entity.QProduct;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Optional<Product> findProductDetail(final Long id) {
		QProduct product = QProduct.product;

		return Optional.ofNullable(
			jpaQueryFactory
				.selectFrom(product)
				.join(product.carType).fetchJoin()
				.join(product.label).fetchJoin()
				.join(product.brand).fetchJoin()
				.join(product.color).fetchJoin()
				.where(product.id.eq(id))
				.fetchOne());
	}

	/**
	 * select *
	 * from products p
	 *          inner join brand br on p.brand_no = br.brand_no
	 *          inner join car_types ct on p.car_type_no = ct.car_type_no
	 *          inner join labels l on p.label_no = l.label_no
	 * where product_no = ${productId};
	 */

	@Override
	public Optional<Product> findSpecificProduct(final Long productId) {
		QProduct product = QProduct.product;

		return Optional.ofNullable(
			jpaQueryFactory
				.selectFrom(product)
				.join(product.brand)
				.join(product.carType)
				.join(product.label)
				.where(product.id.eq(productId))
				.fetchOne()
		);
	}

}
