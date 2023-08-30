package xyz.iwasacar.api.domain.histories.controller;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.histories.dto.PurchaseHistoryDetailResponse;
import xyz.iwasacar.api.domain.histories.dto.PurchaseResponse;
import xyz.iwasacar.api.domain.histories.service.PurchaseHistoryService;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class PurchaseHistoryController {

	private final PurchaseHistoryService purchaseHistoryService;

	@GetMapping("/{memberId}/purchase-histories")
	public ResponseEntity<CommonResponse<PageResponse<PurchaseResponse>>> findAllPurchase(
		@PathVariable final Long memberId, @RequestParam(defaultValue = "0") Integer page,
		@RequestParam(defaultValue = "10") Integer size) {

		PageResponse<PurchaseResponse> allPurchase = purchaseHistoryService.findAllPurchase(memberId, page, size);
		return CommonResponse.success(OK, OK.value(), allPurchase);

	}

	@GetMapping("/{memberId}/purchase-history/{purchase_history_no}")
	public ResponseEntity<CommonResponse<PurchaseHistoryDetailResponse>> findPurchaseDetail(
		@PathVariable final Long memberId, @PathVariable final Long purchaseHistoryNo
	) {
		// 1. cookie에서 memberId를 꺼내온다.
		// 2. PathVariable에 있는 memberId와 비교한다. --> yes/no
		// 3. find 구매내역에 있는 memberId By purchasehistory_no(purchasehistory_no)
		// 4. 3값이랑 1번값이랑 비교한다. Yes/no
		// 5. 구매내역을 가져오는 service를 부른 후 반환한다.

		PurchaseHistoryDetailResponse detail = purchaseHistoryService.findPurchaseDetail(purchaseHistoryNo);
		return CommonResponse.success(OK, OK.value(), detail);
	}

}
