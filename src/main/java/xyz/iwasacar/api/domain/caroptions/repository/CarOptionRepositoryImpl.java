package xyz.iwasacar.api.domain.caroptions.repository;

import static xyz.iwasacar.api.domain.caroptions.entity.QCarOption.*;

import java.util.List;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.caroptions.entity.CarOption;
import xyz.iwasacar.api.domain.caroptions.entity.QCarOption;
import xyz.iwasacar.api.domain.caroptions.entity.QProductOption;

@RequiredArgsConstructor
public class CarOptionRepositoryImpl implements CarOptionRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<CarOption> findOptionsByProductId(final Long productId) {
		QCarOption carOption = QCarOption.carOption;
		QProductOption productOption = QProductOption.productOption;

		/**
		 * select *
		 * from car_options
		 * where car_options.id.
		 */
		return jpaQueryFactory
			.selectFrom(carOption)
			.where(carOption.id.in(JPAExpressions
				.select(productOption.id.carOptionId)
				.from(productOption)
				.where(productOption.id.productId.eq(productId))))
			.fetch();
	}

	@Override
	public List<CarOption> findListById(final List<Long> id) {
		return jpaQueryFactory
			.selectFrom(carOption)
			.where(carOption.id.in(id))
			.fetch();
	}

}
