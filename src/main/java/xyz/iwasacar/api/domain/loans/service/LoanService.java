package xyz.iwasacar.api.domain.loans.service;

import java.util.List;

import xyz.iwasacar.api.domain.loans.dto.response.LoanResponse;

public interface LoanService {

	List<LoanResponse> findSpecificLoan(Integer capital, Integer loan, Integer period,
		String redemption, Integer gracePeriod);

}
