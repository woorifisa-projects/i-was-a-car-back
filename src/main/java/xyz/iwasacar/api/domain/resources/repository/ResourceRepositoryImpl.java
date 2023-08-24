package xyz.iwasacar.api.domain.resources.repository;

import static xyz.iwasacar.api.domain.resources.entity.QProductImage.*;
import static xyz.iwasacar.api.domain.resources.entity.QResource.*;
import static xyz.iwasacar.api.domain.roles.entity.QRole.*;
import static xyz.iwasacar.api.domain.roles.entity.RoleName.*;
import static xyz.iwasacar.api.domain.products.entity.QProduct.*;

import java.util.List;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import xyz.iwasacar.api.domain.resources.entity.Resource;

@RequiredArgsConstructor
public class ResourceRepositoryImpl implements ResourceRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<Resource> findByProductId(final Long productId) {

		/**
		 * select *
		 * from resource
		 * where resource_no in (
		 * 		select products_images.product_no
		 * 		from products_images pi
		 * 		where pi.product_no = ${productId}
		 * 			and pi.role = MEMBER
		 * 	);
		 */
		return jpaQueryFactory
			.selectFrom(resource)
			.where(resource.id.in(
				JPAExpressions
					.select(productImage.id.productId)
					.from(productImage)
					.where(
						productImage.id.productId.eq(productId)
							.and(
								productImage.role.id.eq(
									JPAExpressions
										.select(role.id)
										.from(role)
										.where(role.name.eq(ADMIN))
								)
							)
					)
			))
			.fetch();
	}

	@Override
	public List<ProductImage> findByProducts(Long lastProductId) {

		return jpaQueryFactory
			.selectFrom(productImage)
			.join(productImage.product).fetchJoin()
			.join(productImage.resource).fetchJoin()
			.where(productImage.id.productId.eq(
					JPAExpressions
						.select(product.id)
						.from(product)
						.where(productImage.product.id.eq(product.id))
						.limit(1))
				.and(littleThanLastProductId(lastProductId))
				.and(productImage.role.id.eq(
					JPAExpressions
						.select(role.id)
						.from(role)
						.where(role.name.eq(RoleName.ADMIN))
						.limit(1)))
			).limit(10)
			.fetch();
	}

	private BooleanExpression littleThanLastProductId(Long lastProductId) {
		return lastProductId == null ? null : product.id.lt(lastProductId);
	}

}
