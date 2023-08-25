package xyz.iwasacar.api.domain.loans.repository;

import static xyz.iwasacar.api.domain.loans.entity.QLoan.*;

import java.util.List;
import java.util.Objects;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.loans.entity.Loan;
import xyz.iwasacar.api.domain.loans.entity.QLoan;

@RequiredArgsConstructor
public class LoanRepositoryImpl implements LoanRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<Loan> findSpecificLoan(Integer capital, Integer loan,
		String redemption, Integer period, Integer gracePeriod) {
		QLoan qLoan = QLoan.loan;

		return jpaQueryFactory
			.selectFrom(qLoan)
			.where(qLoan.limit.goe(loan)
				.and(qLoan.period.goe(period))
				.and(checkRedemption(redemption))
				.and(greaterOrEqual(gracePeriod))
			)
			.fetch();
	}

	private BooleanExpression checkRedemption(final String redemption) {
		return Objects.isNull(redemption) ? null : loan.redemption.eq(redemption);
	}

	private BooleanExpression greaterOrEqual(final Integer gracePeriod) {
		return Objects.isNull(gracePeriod) ? null : loan.gracePeriod.goe(gracePeriod);
	}

}
