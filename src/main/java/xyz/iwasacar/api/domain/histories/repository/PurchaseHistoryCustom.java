package xyz.iwasacar.api.domain.histories.repository;

import org.springframework.data.domain.Page;

import xyz.iwasacar.api.domain.histories.dto.response.PurchaseHistoryDetailResponse;
import xyz.iwasacar.api.domain.histories.dto.response.PurchaseResponse;

public interface PurchaseHistoryCustom {
	Page<PurchaseResponse> findAllPurchase(Long memberId, Integer page, Integer size);

	PurchaseHistoryDetailResponse findPurchaseDetail(Long purchaseHistoryNo);
}
