package xyz.iwasacar.api.domain.loans.repository;

import java.util.List;

import xyz.iwasacar.api.domain.loans.entity.Loan;

public interface LoanRepositoryCustom {

	List<Loan> findSpecificLoan(Integer capital, Integer loan, String redemption, Integer period, Integer gracePeriod);

}
