package xyz.iwasacar.api.domain.insurances.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.domain.insurances.dto.response.InsuranceResponse;
import xyz.iwasacar.api.domain.insurances.service.InsuranceService;

@RestController
@RequestMapping("/api/v1/insurances")
@RequiredArgsConstructor
public class InsuranceController {

	private final InsuranceService insuranceService;

	@GetMapping
	public ResponseEntity<CommonResponse<List<InsuranceResponse>>> findUsableInsurance() {
		List<InsuranceResponse> usableInsurances = insuranceService.findUsableInsurances();

		return CommonResponse.success(OK, OK.value(), usableInsurances);
	}

}
