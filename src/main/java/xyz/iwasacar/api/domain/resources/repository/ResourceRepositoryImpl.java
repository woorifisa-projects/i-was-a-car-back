package xyz.iwasacar.api.domain.resources.repository;

import static xyz.iwasacar.api.domain.products.entity.QProduct.*;
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

		/**
		 select *
		 from products_images pi
		 join products p
		 on p.product_no = pi.product_no
		 join resources re
		 on pi.resource_no = re.resource_no
		 where
		 p.product_no = (
		 select pi.product_no
		 from products_images pi
		 where pi.product_no = p.product_no
		 order by pi.resource_no
		 limit 1
		 )
		 and p.product_no < 11
		 and pi.role_no = (
		 select r.role_no
		 from roles r
		 where r.name = 'ADMIN'
		 )
		 order by pi.product_no desc
		 limit 10
		 ;
		 */
		return jpaQueryFactory
			.selectFrom(productImage)
			.join(productImage.product).fetchJoin()
			.join(productImage.resource).fetchJoin()
			.where(productImage.id.productId.eq(
					JPAExpressions
						.select(productImage.id.productId)
						.from(productImage)
						.where(productImage.product.status.ne(EntityStatus.DELETED)
							.and(productImage.product.id.eq(productImage.id.productId)))
						.groupBy(productImage.id.productId, productImage.id.resourceId)
						.orderBy(productImage.id.resourceId.asc())
						.limit(1))
				.and(littleThanLastProductId(lastProductId))
				.and(setCategory(category, keyword))
				.and(
					productImage.role.id.eq(
						JPAExpressions
							.select(role.id)
							.from(role)
							.where(role.name.eq(ADMIN))
							.limit(1)
					)
				)
			).orderBy(productImage.id.productId.desc(), productImage.id.resourceId.asc())
			.limit(10)
			.fetch();

	}

	@Override
	public List<ProductImage> findByProducts(final int page, final int size) {
		return jpaQueryFactory
			.selectFrom(productImage)
			.join(productImage.product).fetchJoin()
			.join(productImage.resource).fetchJoin()
			.where(productImage.id.productId.eq(
					JPAExpressions
						.select(product.id)
						.from(product)
						.where(productImage.id.productId.eq(product.id))
						.limit(1))
				.and(
					productImage.role.id.eq(
						JPAExpressions
							.select(role.id)
							.from(role)
							.where(role.name.eq(ADMIN))
							.limit(1)
					)
				)
			).orderBy(productImage.id.productId.desc(), productImage.id.resourceId.asc())
			.limit(10)
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
			.where(productImage.product.id.eq(
					JPAExpressions
						.select(product.id)
						.from(product)
						.where(productImage.id.productId.eq(product.id)
							.and(product.status.ne(EntityStatus.DELETED)))
						.limit(1))
				.and(littleThanLastProductId(lastProductId))
				.and(productImage.product.carType.id.eq(carType))
				.and(productImage.product.price.loe(capital + loan))
				.and(
					productImage.role.id.eq(
						JPAExpressions
							.select(role.id)
							.from(role)
							.where(role.name.eq(ADMIN))
							.limit(1)
					)
				)
			).orderBy(productImage.id.productId.desc(), productImage.id.resourceId.asc())
			.limit(3)
			.fetch();

		//최신 순으로 3개씩 해서 무한 스크롤
	}

	@Override
	public List<ProductImage> findBySepcificProducts(Long carType, Integer capital, Integer loan, Long lastProductId) {
		return jpaQueryFactory
			.selectFrom(productImage)
			.join(productImage.product).fetchJoin()
			.join(productImage.resource).fetchJoin()
			.where(productImage.product.id.eq(
					JPAExpressions
						.select(product.id)
						.from(product)
						.where(productImage.id.productId.eq(product.id))
						.limit(1)
						.orderBy(productImage.id.resourceId.asc()))
				.and(littleThanLastProductId(lastProductId))
				.and(productImage.product.carType.id.eq(carType))
				.and(productImage.product.price.loe(capital + loan))
				.and(
					productImage.role.id.eq(
						JPAExpressions
							.select(role.id)
							.from(role)
							.where(role.name.eq(ADMIN))
							.limit(1)
					)
				)
			).orderBy(productImage.product.id.desc(), productImage.resource.id.asc())
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

		return category == 1 ? productImage.product.name.like('%' + keyword + '%') :
			productImage.product.brand.name.like('%' + keyword + '%');
	}

}
