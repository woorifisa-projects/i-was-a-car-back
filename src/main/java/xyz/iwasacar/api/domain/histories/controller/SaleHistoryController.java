package xyz.iwasacar.api.domain.histories.controller;

import static org.springframework.http.HttpStatus.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleHistoryDetailResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleHistoryResponse;
import xyz.iwasacar.api.domain.histories.service.SaleHistoryService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/members")
public class SaleHistoryController {

	private final SaleHistoryService saleHistoryService;

	@GetMapping("/{memberId}/sale-histories")
	public ResponseEntity<CommonResponse<PageResponse<SaleHistoryResponse>>> findSaleHistories(
		@PathVariable final Long memberId
		, @RequestParam(required = false, defaultValue = "1") final Integer page
		, @RequestParam(required = false, defaultValue = "10") final Integer size
	) {

		// session -> refreshToken -> memberId 비교

		PageResponse<SaleHistoryResponse> saleHistories = saleHistoryService
			.findSaleHistoriesByMember(memberId, page, size);

		return CommonResponse.success(OK, OK.value(), saleHistories);
	}

	@GetMapping("/{memberId}/sale-histories/{saleHistoryId}")
	public ResponseEntity<CommonResponse<SaleHistoryDetailResponse>> findSaleHistoryDetail(@PathVariable Long memberId,
		@PathVariable Long saleHistoryId, HttpServletRequest request) {

		// 1. request를 통해서 memberId를 꺼내와 매개변수 memberId와 비교한다.
		// Long authorization = Long.valueOf(String.valueOf(request.getAttribute("Authorization")));

		// if (!authorization.equals(memberId))
		// 	throw new HistoryNotFound();

		// 2. saleHistoryId를 통해 판매 내역에 있는 memberId 찾기

		// 3. 1번 값이랑 2번 값이랑 비교한다. Yes/no

		// 4. 판매내역을 가져오는 service를 부른 후 반환한다.
		SaleHistoryDetailResponse specificSaleHistory = saleHistoryService.findSaleHistoryDetail(saleHistoryId);

		return CommonResponse.success(OK, OK.value(), specificSaleHistory);
	}

}
