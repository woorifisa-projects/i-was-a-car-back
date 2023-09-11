package xyz.iwasacar.api.domain.products.repository;

import static xyz.iwasacar.api.domain.common.constant.EntityStatus.*;
import static xyz.iwasacar.api.domain.products.entity.QProduct.*;

import java.util.List;
import java.util.Objects;
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

	//ProductResponse 객체 생성할 때, image를 null값으로 주니까 에러발생해서 일단 product.color.name
	@Override
	public Page<ProductResponse> findWaitingProducts(Integer page, Integer size) {
		int offset = (page - 1) * size;

		List<ProductResponse> content = jpaQueryFactory
			.select(Projections.constructor(
				ProductResponse.class,
				product.id, product.carType.name,product.name, product.brand.name, product.info, product.label.name, product.fuel,
				product.drivingMethod, product.displacement, product.year, product.distance, product.price,
				product.fuel, product.createdAt
			))
			.from(product)
			.join(product.brand)
			.join(product.label)
			.where(product.label.id.eq(1L))
			.offset(offset)
			.limit(size)
			.fetch();

		long totalCount = Objects.requireNonNull(
			jpaQueryFactory
				.select(product.count())
				.from(product)
				.where(product.label.id.eq(1L))
				.fetchOne()
		);

		// 	Optional.ofNullable(
		// 	jpaQueryFactory
		// 		.select(product.count())
		// 		.from(product)
		// 		.fetchOne()
		// ).orElse(1L);

		PageRequest pageable = PageRequest.of(page - 1, size);

		return new PageImpl<>(content, pageable, totalCount);
	}

	//ProductResponse 객체 생성할 때, image를 null값으로 주니까 에러발생해서 일단 product.color.name
	@Override
	public Page<ProductResponse> findProductsForAdmin(Integer page, Integer size) {
		int offset = (page - 1) * size;

		List<ProductResponse> content = jpaQueryFactory
			.select(Projections.constructor(
				ProductResponse.class,
				product.id, product.carType.name,product.name, product.brand.name, product.info, product.label.name, product.fuel,
				product.drivingMethod, product.displacement, product.year, product.distance, product.price,
				product.fuel, product.createdAt
			))
			.from(product)
			.join(product.brand)
			.join(product.label)
			.offset(offset)
			.limit(size)
			.fetch();

		Long totalCount =

			Optional.ofNullable(
				jpaQueryFactory
					.select(product.count())
					.from(product)
					.fetchOne()
			).orElse(1L);
		System.out.println(page);
		PageRequest pageable = PageRequest.of(page - 1, size);

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
