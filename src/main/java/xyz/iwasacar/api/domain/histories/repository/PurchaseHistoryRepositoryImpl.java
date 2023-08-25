package xyz.iwasacar.api.domain.histories.repository;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.histories.dto.PurchaseResponse;
import xyz.iwasacar.api.domain.histories.entity.QPurchaseHistory;

@RequiredArgsConstructor
public class PurchaseHistoryRepositoryImpl implements PurchaseHistoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	public Page<PurchaseResponse> findAllPurchase(Long memberId, Integer page, Integer size) {
		QPurchaseHistory purchaseHistory = QPurchaseHistory.purchaseHistory;

		// 실제로 데이터베이스에서 데이터를 조회하는 쿼리 작성
		// List<PurchaseHistory> purchaseHistories = jpaQueryFactory
		// 	.selectFrom(purchaseHistory)
		// 	.fetchJoin()
		// 	.where(purchaseHistory.member.id.eq(memberId))
		// 	.offset(page * size)
		// 	.limit(size)
		// 	.fetch();

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
}
