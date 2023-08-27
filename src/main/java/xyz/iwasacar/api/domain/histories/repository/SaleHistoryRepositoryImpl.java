package xyz.iwasacar.api.domain.histories.repository;

import static xyz.iwasacar.api.domain.histories.entity.QSaleHistory.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.histories.dto.response.SaleHistoryResponse;
import xyz.iwasacar.api.domain.histories.entity.QSaleHistory;
import xyz.iwasacar.api.domain.histories.entity.SaleHistory;

@RequiredArgsConstructor
public class SaleHistoryRepositoryImpl implements SaleHistoryRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Page<SaleHistoryResponse> findSaleHistories(Long memberId, Integer page,
		Integer size) {

		QSaleHistory saleHistory = QSaleHistory.saleHistory;

		int offset = (page - 1) * size;

		List<SaleHistoryResponse> list = jpaQueryFactory
			.select(Projections.constructor(
				SaleHistoryResponse.class, saleHistory.product.id,
				saleHistory.createdAt,
				saleHistory.product.name,
				saleHistory.product.label.name)
			).from(saleHistory)
			.join(saleHistory.product)
			.join(saleHistory.product.label)
			.orderBy(saleHistory.id.desc())
			.offset(offset)
			.limit(size)
			.fetch();

		long totalCount = Objects.requireNonNull(
			jpaQueryFactory
				.select(saleHistory.count())
				.from(saleHistory)
				.fetchOne());

		return new PageImpl<>(list, PageRequest.of(page - 1, size), totalCount);
	}

	/**
	 * select *
	 * from sales_histories sh
	 * inner join banks b on sh.bank_no = b.bank_no
	 * inner join products p on sh.product_no = p.product_no
	 * where sales_history_no = ${saleHistoryId};
	 */

	@Override
	public Optional<SaleHistory> findSpecificSaleHistory(final Long saleHistoryId) {

		return Optional.ofNullable(
			jpaQueryFactory
				.selectFrom(saleHistory)
				.join(saleHistory.bank).fetchJoin()
				.join(saleHistory.product).fetchJoin()
				.where(saleHistory.id.eq(saleHistoryId))
				.fetchOne()
		);

	}

}






