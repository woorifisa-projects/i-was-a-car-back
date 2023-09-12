package xyz.iwasacar.api.domain.resources.repository;

import static xyz.iwasacar.api.domain.resources.entity.QProductImage.*;
import static xyz.iwasacar.api.domain.resources.entity.QResource.*;
import static xyz.iwasacar.api.domain.roles.entity.QRole.*;
import static xyz.iwasacar.api.domain.roles.entity.RoleName.*;

import java.util.List;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.common.constant.EntityStatus;
import xyz.iwasacar.api.domain.resources.entity.ProductImage;
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
					.select(productImage.id.resourceId)
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
	public List<ProductImage> findByProducts(final Long category, final String keyword, final Long lastProductId) {
		return jpaQueryFactory
			.selectFrom(productImage)
			.join(productImage.product).fetchJoin()
			.join(productImage.resource).fetchJoin()
			.where(
				productImage.role.id.eq(
						JPAExpressions
							.select(role.id)
							.from(role)
							.where(role.name.eq(ADMIN))
							.limit(1))
					.and(productImage.product.status.ne(EntityStatus.DELETED))
					.and(littleThanLastProductId(lastProductId))
					.and(setCategory(category, keyword))
			)
			.groupBy(productImage.product.id)
			.having(productImage.resource.id.eq(productImage.resource.id.min()))
			.orderBy(productImage.product.id.desc())
			.fetch();
	}

	@Override
	public List<ProductImage> findByProducts(final int page, final int size) {
		int offset = (page - 1) * size;

		return jpaQueryFactory
			.selectFrom(productImage)
			.join(productImage.product).fetchJoin()
			.join(productImage.resource).fetchJoin()
			.where(
				productImage.role.id.eq(
						JPAExpressions
							.select(role.id)
							.from(role)
							.where(role.name.eq(ADMIN))
							.limit(1))
					.and(productImage.product.status.ne(EntityStatus.DELETED))
			)
			.groupBy(productImage.product.id)
			.having(productImage.resource.id.eq(productImage.resource.id.min()))
			.orderBy(productImage.product.id.desc())
			.limit(size)
			.offset(offset)
			.fetch();
	}

	@Override
	public List<ProductImage> findBySpecificProducts(
		final Long carType, final Integer capital, final Integer loan, final Long lastProductId
	) {

		return jpaQueryFactory
			.selectFrom(productImage)
			.join(productImage.product).fetchJoin()
			.join(productImage.resource).fetchJoin()
			.where(
				productImage.role.id.eq(
						JPAExpressions
							.select(role.id)
							.from(role)
							.where(role.name.eq(ADMIN))
							.limit(1))
					.and(productImage.product.status.ne(EntityStatus.DELETED))
					.and(littleThanLastProductId(lastProductId))
					.and(productImage.product.carType.id.eq(carType))
					.and(productImage.product.price.loe(capital + loan))
			)
			.groupBy(productImage.product.id)
			.having(productImage.resource.id.eq(productImage.resource.id.min()))
			.orderBy(productImage.product.id.desc())
			.limit(3)
			.fetch();

		//최신 순으로 3개씩 해서 무한 스크롤
	}

	private BooleanExpression littleThanLastProductId(final Long lastProductId) {
		return lastProductId == null ? null : productImage.product.id.lt(lastProductId);
	}

	private BooleanExpression setCategory(Long category, String keyword) {
		if (category == null || keyword == null) {
			return null;
		}

		String likeKeyword = '%' + keyword + '%';

		return category == 1 ? productImage.product.name.like(likeKeyword) :
			productImage.product.brand.name.like(likeKeyword);
	}

}
