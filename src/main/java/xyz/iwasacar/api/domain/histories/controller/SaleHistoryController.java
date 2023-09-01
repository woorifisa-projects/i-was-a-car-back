package xyz.iwasacar.api.domain.histories.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.histories.dto.request.SaleRequest;
import xyz.iwasacar.api.domain.histories.dto.response.SaleHistoryDetailResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleHistoryResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleResponse;
import xyz.iwasacar.api.domain.histories.service.SaleService;

@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
public class SaleHistoryController {

	private final SaleService saleService;

	@PostMapping(
		consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommonResponse<SaleResponse>> saveSalesHistory(
		@RequestPart SaleRequest saleRequest,
		@RequestPart List<MultipartFile> carImages,
		@RequestPart MultipartFile performanceCheck
		// @Login MemberClaim memberClaim
	) {

		SaleResponse saleResponse = saleService.saveSalesHistory(saleRequest, carImages,
			performanceCheck, 1L);

		return CommonResponse.success(CREATED, CREATED.value(), saleResponse);
	}

	@GetMapping("/{memberId}/sale-histories")
	public ResponseEntity<CommonResponse<PageResponse<SaleHistoryResponse>>> findSaleHistories(
		@PathVariable final Long memberId
		, @RequestParam(required = false, defaultValue = "1") final Integer page
		, @RequestParam(required = false, defaultValue = "10") final Integer size
		// , @Login MemberClaim memberClaim
	) {
		//memberClaim에서의 id ,PathVariable id 비교 -> 모든 메서드에서도 해야함.
		PageResponse<SaleHistoryResponse> saleHistories = saleService
			.findSaleHistoriesByMember(memberId, page, size);

		return CommonResponse.success(OK, OK.value(), saleHistories);
	}

	@GetMapping("/{memberId}/sale-histories/{saleHistoryId}")
	public ResponseEntity<CommonResponse<SaleHistoryDetailResponse>> findSaleHistoryDetail(@PathVariable Long memberId,
		@PathVariable Long saleHistoryId
		//, @Login MemberClaim memberClaim
	) {

		SaleHistoryDetailResponse specificSaleHistory = saleService.findSaleHistoryDetail(saleHistoryId);

		return CommonResponse.success(OK, OK.value(), specificSaleHistory);
	}





	@PostMapping("/api-docs")
	public void forSwagger(@RequestBody SaleRequest saleRequest) {
		throw new RuntimeException();
	}

}


