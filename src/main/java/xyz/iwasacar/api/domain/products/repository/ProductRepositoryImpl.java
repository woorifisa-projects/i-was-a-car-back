package xyz.iwasacar.api.domain.products.repository;

import static xyz.iwasacar.api.domain.common.constant.EntityStatus.*;
import static xyz.iwasacar.api.domain.products.entity.QProduct.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.products.dto.response.ProductResponse;
import xyz.iwasacar.api.domain.products.entity.Product;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Page<ProductResponse> findProductsForAdmin(int page, int size) {
		int offset = (page - 1) * size;

		List<ProductResponse> content = jpaQueryFactory
			.select(Projections.constructor(
				ProductResponse.class,
				product.id, product.name, product.brand.name, product.label.name,
				product.fuel, product.drivingMethod, product.displacement, product.year,
				product.distance, product.price, null, product.createdAt
			))
			.from(product)
			.join(product.brand)
			.join(product.label)
			.offset(offset)
			.limit(size)
			.fetch();

		Long totalCount = Optional.ofNullable(
			jpaQueryFactory
				.select(product.count())
				.from(product)
				.fetchOne()
		).orElse(1L);

		PageRequest pageable = PageRequest.of(page, size);

		return new PageImpl<>(content, pageable, totalCount);
	}

	@Override
	public Optional<Product> findProductDetail(final Long id) {
		return Optional.ofNullable(
			jpaQueryFactory
				.selectFrom(product)
				.join(product.carType).fetchJoin()
				.join(product.label).fetchJoin()
				.join(product.brand).fetchJoin()
				.join(product.color).fetchJoin()
				.where(
					product.id.eq(id).and(
						product.status.ne(DELETED)
					))
				.fetchOne());
	}

	@Override
	public Optional<Product> findProductDetailForAdmin(final Long id) {

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
