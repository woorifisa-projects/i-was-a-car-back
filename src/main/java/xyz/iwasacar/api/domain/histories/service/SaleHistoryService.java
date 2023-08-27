package xyz.iwasacar.api.domain.histories.service;

import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleHistoryDetailResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleHistoryResponse;

public interface SaleHistoryService {

	PageResponse<SaleHistoryResponse> findSaleHistoriesByMember(final Long memberId,
		final Integer page,
		final Integer size);

	SaleHistoryDetailResponse findSaleHistoryDetail(final Long saleHistoryId);
}

