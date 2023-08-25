package xyz.iwasacar.api.domain.histories.repository;

import org.springframework.data.domain.Page;

import xyz.iwasacar.api.domain.histories.dto.PurchaseResponse;

public interface PurchaseHistoryCustom {
	Page<PurchaseResponse> findAllPurchase(Long memberId, Integer page, Integer size);
}
