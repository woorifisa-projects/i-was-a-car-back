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

}
