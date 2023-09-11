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
import xyz.iwasacar.api.domain.histories.dto.response.SaleHistoryDetailResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleHistoryResponse;
import xyz.iwasacar.api.domain.histories.entity.QSaleHistory;
import xyz.iwasacar.api.domain.histories.entity.SaleHistory;
import xyz.iwasacar.api.domain.histories.exception.SaleHistoryNotFoundException;

@RequiredArgsConstructor
public class SaleHistoryRepositoryImpl implements SaleHistoryRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	public Page<SaleHistoryResponse> findSales(Long memberId, Integer page, Integer size) {
		QSaleHistory saleHistory = QSaleHistory.saleHistory;

		int offset = (page - 1) * size;

		List<SaleHistoryResponse> list = jpaQueryFactory
			.select(Projections.constructor(
				SaleHistoryResponse.class, saleHistory.id, saleHistory.product.name,
				saleHistory.product.label.name, saleHistory.createAt))
			.from(saleHistory)
			.join(saleHistory.product)
			.join(saleHistory.product.label)
			.where(saleHistory.member.id.eq(memberId))
			.orderBy(saleHistory.id.desc())
			.offset(offset)
			.limit(size)
			.fetch();

		long totalCount = Objects.requireNonNull(
			jpaQueryFactory
				.select(saleHistory.count())
				.from(saleHistory)
				.where(saleHistory.member.id.eq(memberId))
				.fetchOne());

		return new PageImpl<>(list, PageRequest.of(page - 1, size), totalCount);

	}

	@Override
	public SaleHistoryDetailResponse findSaleDetail(Long saleHistoryNo) {
		QSaleHistory saleHistory = QSaleHistory.saleHistory;

		return jpaQueryFactory
			.select(Projections.constructor(
				SaleHistoryDetailResponse.class, saleHistory.member.name, saleHistory.member.tel, saleHistory.zipCode,
				saleHistory.address, saleHistory.meetingSchedule, saleHistory.product.name, saleHistory.createAt,
				saleHistory.product.price, saleHistory.bank.name, saleHistory.accountHolder, saleHistory.accountNumber,
				saleHistory.product.label.name))
			.from(saleHistory)
			.join(saleHistory.product)
			.join(saleHistory.member)
			.join(saleHistory.bank)
			.join(saleHistory.product.label)
			.where(saleHistory.id.eq(saleHistoryNo))
			.fetchOne();
	}

	@Override
	public SaleHistory findWithMemberAndProductByProductId(final Long productId) {

		return Optional.ofNullable(

			jpaQueryFactory
				.selectFrom(saleHistory)
				.join(saleHistory.product).fetchJoin()
				.join(saleHistory.member).fetchJoin()
				.where(saleHistory.product.id.eq(productId))
				.fetchOne()
		).orElseThrow(SaleHistoryNotFoundException::new);
	}

	@Override
	public SaleHistory findByProductId(final Long productId) {
		return Optional.ofNullable(

			jpaQueryFactory
				.selectFrom(saleHistory)
				.join(saleHistory.member).fetchJoin()
				.where(saleHistory.product.id.eq(productId))
				.fetchOne()
		).orElseThrow(SaleHistoryNotFoundException::new);
	}
}
