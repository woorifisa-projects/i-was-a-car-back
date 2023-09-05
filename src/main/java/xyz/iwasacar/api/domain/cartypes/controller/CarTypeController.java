package xyz.iwasacar.api.domain.cartypes.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.domain.cartypes.dto.response.CarTypeResponse;
import xyz.iwasacar.api.domain.cartypes.service.CarTypeService;

@RestController
@RequestMapping("/api/v1/car-types")
@RequiredArgsConstructor
public class CarTypeController {

	private final CarTypeService carTypeService;

	@GetMapping
	public ResponseEntity<CommonResponse<List<CarTypeResponse>>> findCarTypes() {
		List<CarTypeResponse> carTypes = carTypeService.findCarTypes();
		return CommonResponse.success(OK, OK.value(), carTypes);
	}

}
