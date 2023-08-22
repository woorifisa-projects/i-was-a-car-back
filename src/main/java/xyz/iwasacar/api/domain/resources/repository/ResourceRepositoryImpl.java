package xyz.iwasacar.api.domain.resources.repository;

import java.util.List;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.resources.entity.QProductImage;
import xyz.iwasacar.api.domain.resources.entity.QResource;
import xyz.iwasacar.api.domain.resources.entity.Resource;
import xyz.iwasacar.api.domain.roles.entity.RoleName;

@RequiredArgsConstructor
public class ResourceRepositoryImpl implements ResourceRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<Resource> findByProductId(final Long productId) {
		QResource resource = QResource.resource;
		QProductImage productImage = QProductImage.productImage;

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
								productImage.role.name.eq(RoleName.ADMIN)
							)
					)
			))
			.fetch();
	}

	@Override
	public List<Resource> findByProducts(List<Long> lastProductIdList) {
		QResource resource = QResource.resource;
		QProductImage productImage = QProductImage.productImage;

		return jpaQueryFactory
			.selectFrom(resource)
			.where(resource.id.in(
				JPAExpressions
					.select(productImage.id.productId)
					.from(productImage)
					.where(
						productImage.id.productId.in(lastProductIdList)
							.and(
								productImage.role.name.eq(RoleName.ADMIN)
							)
					)
					.limit(1)
			))
			.fetch();
	}

}
