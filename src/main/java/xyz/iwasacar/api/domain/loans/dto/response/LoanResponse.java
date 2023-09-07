package xyz.iwasacar.api.domain.loans.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.loans.entity.Loan;

@RequiredArgsConstructor
@Getter
public class LoanResponse {
	private final Long id;
	private final String name;
	private final Integer limit;
	private final Double interestRate;
	private final String redemption;
	private final Integer period;
	private final Integer monthlyPayment;

	private final Integer customerLoan;
	private final Integer customerPeriod;

	public static LoanResponse of(final Loan loan, final Integer customerLoan, final Integer customerPeriod) {
		return new LoanResponse(loan.getId(), loan.getName(), loan.getLimit(), loan.getInterestRate(),
			loan.getRedemption(), loan.getPeriod(), customerLoan / customerPeriod, customerLoan, customerPeriod);
	}

}
