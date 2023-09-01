package xyz.iwasacar.api.domain.histories.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.histories.dto.request.SaleRequest;
import xyz.iwasacar.api.domain.histories.dto.response.SaleHistoryDetailResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleHistoryResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleResponse;

public interface SaleService {

	SaleResponse saveSalesHistory(SaleRequest saleRequest, List<MultipartFile> carImages,
		MultipartFile performanceCheck, Long memberId);

	PageResponse<SaleHistoryResponse> findSaleHistoriesByMember(Long memberId, Integer page, Integer size);

	SaleHistoryDetailResponse findSaleHistoryDetail(Long saleHistoryId);
}
