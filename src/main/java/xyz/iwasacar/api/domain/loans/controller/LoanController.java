package xyz.iwasacar.api.domain.loans.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/loans")
@RequiredArgsConstructor
public class LoanController {

	private final LoanController loanController;

	/**
	 *
	 * @param capital        자본금
	 * @param loan            원하는 대출금
	 * @param redemption    상환방법
	 * @param period        상환기간 (12개월 단위)
	 * @param gracePeriod    거치기간
	 *
	 */
	@GetMapping
	public Void findSpecificLoan(
		@RequestParam Integer capital, @RequestParam Integer loan,
		@RequestParam String redemption, @RequestParam Integer period, @RequestParam Integer gracePeriod) {

		return null;
	}

}
