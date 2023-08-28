package xyz.iwasacar.api.domain.histories.repository;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.histories.dto.PurchaseHistoryDetailResponse;
import xyz.iwasacar.api.domain.histories.dto.PurchaseResponse;
import xyz.iwasacar.api.domain.histories.entity.QPurchaseHistory;

@RequiredArgsConstructor
public class PurchaseHistoryRepositoryImpl implements PurchaseHistoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	public Page<PurchaseResponse> findAllPurchase(Long memberId, Integer page, Integer size) {
		QPurchaseHistory purchaseHistory = QPurchaseHistory.purchaseHistory;

		int offset = (page - 1) * size;

		List<PurchaseResponse> list = jpaQueryFactory
			.select(Projections.constructor(
				PurchaseResponse.class, purchaseHistory.id, purchaseHistory.product.name,
				purchaseHistory.product.label.name, purchaseHistory.createAt))
			.from(purchaseHistory)
			.join(purchaseHistory.product)
			.join(purchaseHistory.product.label)
			.where(purchaseHistory.member.id.eq(memberId))
			.orderBy(purchaseHistory.id.desc())
			.offset(offset)
			.limit(size)
			.fetch();

		long totalCount = Objects.requireNonNull(
			jpaQueryFactory
				.select(purchaseHistory.count())
				.from(purchaseHistory)
				.fetchOne());

		return new PageImpl<>(list, PageRequest.of(page - 1, size), totalCount);

	}

	// DTO에서 from을 사용하는데,, 흠.. 이렇게 만드는 방법말고 더 좋은 바업ㅂ이 있을거 같단 말이지
	@Override
	public PurchaseHistoryDetailResponse findDetailPurchase(Long purchaseHistoryNo) {
		QPurchaseHistory purchaseHistory = QPurchaseHistory.purchaseHistory;

		PurchaseHistoryDetailResponse purchaseHistoryDetailResponse = jpaQueryFactory
			.select(Projections.constructor(
				PurchaseHistoryDetailResponse.class, purchaseHistory.id, purchaseHistory.createAt,
				purchaseHistory.deliverySchedule,
				purchaseHistory.member.name, purchaseHistory.member.tel,
				purchaseHistory.product.name, purchaseHistory.product.price, purchaseHistory.product.label.name,
				purchaseHistory.product.name,
				purchaseHistory.loan.name,
				purchaseHistory.insurance.name))
			.from(purchaseHistory)
			.join(purchaseHistory.product)
			.join(purchaseHistory.member)
			.join(purchaseHistory.loan)
			.join(purchaseHistory.insurance)
			.where(purchaseHistory.id.eq(purchaseHistoryNo))
			.fetchOne();

		return purchaseHistoryDetailResponse;
	}
}
