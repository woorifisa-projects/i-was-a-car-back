package xyz.iwasacar.api.domain.histories.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping
	public ResponseEntity<CommonResponse<SaleResponse>> saveSalesHistory(
		@RequestPart List<MultipartFile> carImages,
		@RequestPart @Valid SaleRequest saleRequest
	) {

		SaleResponse saleResponse = saleService.saveSalesHistory(carImages, saleRequest);

		return CommonResponse.success(CREATED, CREATED.value(), saleResponse);
	}

}
