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
		// String cookieById = String.valueOf(request.getAttribute("Authorization"));
		// if (memberId == Integer.parseInt(cookieById)) {
		PageResponse<PurchaseResponse> allPurchase = purchaseHistoryService.findAllPurchase(memberId, page, size);
		return CommonResponse.success(OK, OK.value(), allPurchase);
		// }

		// throw new IllegalArgumentException("접근 권한이 없다.");
	}

	@
}
