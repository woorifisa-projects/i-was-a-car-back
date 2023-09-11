package xyz.iwasacar.api.domain.histories.controller;

import static org.springframework.http.HttpStatus.*;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.domain.histories.dto.request.PurchaseHistoryRequest;
import xyz.iwasacar.api.domain.histories.dto.response.PurchaseHistoryResponse;
import xyz.iwasacar.api.domain.histories.service.PurchaseService;

@RestController
@RequestMapping("/api/v1/histories")
@RequiredArgsConstructor
public class HistoryController {

	private final PurchaseService purchaseService;

	@PostMapping("/purchase")
	public ResponseEntity<CommonResponse<PurchaseHistoryResponse>> savePurchaseHistory(
		@RequestBody @Valid PurchaseHistoryRequest purchaseHistoryRequest) {
		PurchaseHistoryResponse savedPurchaseHistory = purchaseService.savePurchaseHistory(purchaseHistoryRequest);
		return CommonResponse.success(CREATED, CREATED.value(), savedPurchaseHistory);
	}

}
