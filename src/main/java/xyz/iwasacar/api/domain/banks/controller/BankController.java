package xyz.iwasacar.api.domain.banks.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.domain.banks.dto.response.BankResponse;
import xyz.iwasacar.api.domain.banks.service.BankService;

@RestController
@RequestMapping("/api/v1/banks")
@RequiredArgsConstructor
public class BankController {

	private final BankService bankService;

	@GetMapping
	public ResponseEntity<CommonResponse<List<BankResponse>>> findBanks() {
		List<BankResponse> banks = bankService.findBanks();
		return CommonResponse.success(OK, OK.value(), banks);
	}

}
