package xyz.iwasacar.api.domain.histories.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.domain.histories.dto.request.SaleRequest;
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

	@PostMapping("/api-docs")
	public void forSwagger(@RequestBody SaleRequest saleRequest) {
		throw new RuntimeException();
	}

}