package xyz.iwasacar.api.domain.histories.repository;

import org.springframework.data.domain.Page;

import xyz.iwasacar.api.domain.histories.dto.response.SaleHistoryDetailResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleHistoryResponse;

public interface SaleHistoryRepositoryCustom {
	Page<SaleHistoryResponse> findSales(Long memberId, Integer page, Integer size);

	SaleHistoryDetailResponse findSaleDetail(Long purchaseHistoryNo);
}
