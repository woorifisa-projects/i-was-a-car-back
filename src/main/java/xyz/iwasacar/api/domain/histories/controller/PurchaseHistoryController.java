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
import xyz.iwasacar.api.domain.histories.dto.response.PurchaseHistoryDetailResponse;
import xyz.iwasacar.api.domain.histories.dto.response.PurchaseResponse;
import xyz.iwasacar.api.domain.histories.service.PurchaseHistoryService;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class PurchaseHistoryController {

	private final PurchaseHistoryService purchaseHistoryService;

	@GetMapping("/{memberId}/purchase-histories")
	public ResponseEntity<CommonResponse<PageResponse<PurchaseResponse>>> findAllPurchase(
		@PathVariable final Long memberId, @RequestParam(defaultValue = "1") Integer page,
		@RequestParam(defaultValue = "8") Integer size
		// , @Login MemberClaim memberClaim
	) {

		PageResponse<PurchaseResponse> allPurchase = purchaseHistoryService.findAllPurchase(memberId, page, size);
		return CommonResponse.success(OK, OK.value(), allPurchase);

	}

	@GetMapping("/{memberId}/purchase-history/{purchaseHistoryNo}")
	public ResponseEntity<CommonResponse<PurchaseHistoryDetailResponse>> findPurchaseDetail(
		@PathVariable final Long memberId, @PathVariable final Long purchaseHistoryNo
		//@Login MemberClaim memberclaim
	) {
		PurchaseHistoryDetailResponse detail = purchaseHistoryService.findPurchaseDetail(purchaseHistoryNo);
		return CommonResponse.success(OK, OK.value(), detail);
	}

}
