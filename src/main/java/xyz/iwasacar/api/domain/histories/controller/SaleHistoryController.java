package xyz.iwasacar.api.domain.histories.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.springframework.http.MediaType;
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
		consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommonResponse<SaleResponse>> saveSalesHistory(
		@RequestPart ProductCreateRequest productCreateRequest,
		@RequestPart List<MultipartFile> carImages,
		@Login MemberClaim memberClaim
	) {

		System.out.println(productCreateRequest);
		carImages.forEach(System.out::println);

		SaleResponse saleResponse = saleService
			.saveSalesHistory(productCreateRequest, carImages, memberClaim.getMemberId());

		return CommonResponse.success(CREATED, CREATED.value(), saleResponse);
	}

	@PostMapping("/api-docs")
	public void forSwagger(@RequestBody ProductCreateRequest productCreateRequest) {
		throw new RuntimeException();
	}

}
