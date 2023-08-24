package xyz.iwasacar.api.domain.loans.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.domain.loans.dto.response.LoanResponse;
import xyz.iwasacar.api.domain.loans.service.LoanService;

@RestController
@RequestMapping("/api/v1/loans")
@RequiredArgsConstructor
public class LoanController {

	private final LoanService loanService;

	/**
	 *
	 * @param capital        자본금
	 * @param loan            원하는 대출금
	 * @param period        상환기간 (12개월 단위)
	 * @param redemption    상환방법
	 * @param gracePeriod    거치기간
	 *
	 */
	@GetMapping
	public ResponseEntity<CommonResponse<List<LoanResponse>>> findSpecificLoan(
		@RequestParam Integer capital, @RequestParam Integer loan, @RequestParam Integer period,
		@RequestParam(required = false) String redemption, @RequestParam(required = false) Integer gracePeriod) {

		List<LoanResponse> specificLoan = loanService.findSpecificLoan(capital, loan, period, redemption, gracePeriod);

		return CommonResponse.success(OK, OK.value(), specificLoan);
	}

}
