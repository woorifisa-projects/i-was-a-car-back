package xyz.iwasacar.api.domain.histories.service;

import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.histories.dto.PurchaseHistoryDetailResponse;
import xyz.iwasacar.api.domain.histories.dto.PurchaseResponse;

public interface PurchaseHistoryService {

	PageResponse<PurchaseResponse> findAllPurchase(Long cookieById, Integer page, Integer size);

	Long findMemberId(Long purchaseHistoryNo);

	PurchaseHistoryDetailResponse findPurchaseDetail(Long purchaseHistoryNo);
}
