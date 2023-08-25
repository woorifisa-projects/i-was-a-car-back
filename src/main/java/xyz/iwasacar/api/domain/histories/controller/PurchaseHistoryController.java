package xyz.iwasacar.api.domain.histories.controller;

import java.util.List;

import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.domain.histories.dto.PurchaseResponse;
import xyz.iwasacar.api.domain.histories.entity.PurchaseHistory;
import xyz.iwasacar.api.domain.histories.service.PurchaseHistoryService;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class PurchaseHistoryController {

	private PurchaseHistoryService purchaseHistoryService;


	@GetMapping("/{memberId}/purchase-histories")
	public ResponseEntity<CommonResponse<List<PurchaseResponse>>> findAllPurchase (
	@PathVariable final Long memberId, HttpRequest request){
		// request.getS
		// List<PurchaseResponse> allPurchase = purchaseHistoryService.findAllPurchase()
		return
	}


}
