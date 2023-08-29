package xyz.iwasacar.api.domain.histories.controller;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.domain.histories.dto.request.PurchaseHistoryRequest;
import xyz.iwasacar.api.domain.histories.dto.response.PurchaseHistoryResponse;
import xyz.iwasacar.api.domain.histories.service.HistoryService;

@RestController
@RequestMapping("/api/v1/histories")
@RequiredArgsConstructor
public class HistoryController {

	private final HistoryService historyService;

	@PostMapping("/purchase")
	public ResponseEntity<CommonResponse<PurchaseHistoryResponse>> savePurchaseHistory(
		@RequestBody PurchaseHistoryRequest purchaseHistoryRequest) {
		PurchaseHistoryResponse savedpurchaseHistory = historyService.savePurchaseHistory(purchaseHistoryRequest);
		return CommonResponse.success(CREATED, CREATED.value(), savedpurchaseHistory);
	}

}
