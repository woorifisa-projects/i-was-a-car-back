package xyz.iwasacar.api.domain.loans.service;

import static java.util.stream.Collectors.*;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.loans.dto.response.LoanResponse;
import xyz.iwasacar.api.domain.loans.repository.LoanRepository;

@Service
@RequiredArgsConstructor
public class DefaultLoanService implements LoanService {

	private final LoanRepository loanRepository;

	public List<LoanResponse> findSpecificLoan(final Integer capital, final Integer loan, final Integer period,
		final String redemption, final Integer gracePeriod) {

		return loanRepository.findSpecificLoan(capital, loan, redemption, period, gracePeriod)
			.stream()
			.map(l -> LoanResponse.of(l, loan, period))
			.collect(toList());
	}

}
