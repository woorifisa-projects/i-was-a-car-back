package xyz.iwasacar.api.domain.histories.controller;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.annotation.Login;
import xyz.iwasacar.api.common.auth.jwt.MemberClaim;
import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.domain.histories.dto.request.ProductCreateRequest;
import xyz.iwasacar.api.domain.histories.dto.response.CarInfoResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleResponse;
import xyz.iwasacar.api.domain.histories.service.SaleService;

@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
public class SaleHistoryController {

	private final SaleService saleService;

	@GetMapping("/car-info")
	public ResponseEntity<CommonResponse<CarInfoResponse>> findCarInfoByCarNumber(
		@RequestParam String name, @RequestParam String carNumber) {
		CarInfoResponse carInfo = saleService.findCarInfoByCarNumber(name, carNumber);
		return CommonResponse.success(OK, OK.value(), carInfo);
	}

	@PostMapping(
		consumes = {MULTIPART_FORM_DATA_VALUE, APPLICATION_JSON_VALUE},
		produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<CommonResponse<SaleResponse>> saveSalesHistory(
		@RequestPart List<MultipartFile> carImages,
		@RequestPart ProductCreateRequest productCreateRequest,
		@Login MemberClaim memberClaim
	) {

		SaleResponse saleResponse = saleService
			.saveSalesHistory(productCreateRequest, carImages, memberClaim.getMemberId());

		return CommonResponse.success(CREATED, CREATED.value(), saleResponse);
	}

	@PostMapping("/api-docs")
	public void forSwagger(@RequestBody ProductCreateRequest productCreateRequest) {
		throw new RuntimeException();
	}

}
