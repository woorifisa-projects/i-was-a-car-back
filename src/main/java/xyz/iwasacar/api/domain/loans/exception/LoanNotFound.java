package xyz.iwasacar.api.domain.loans.exception;

import xyz.iwasacar.api.common.exception.base.BaseAbstractException;
import xyz.iwasacar.api.common.exception.base.ExceptionStatus;

public class LoanNotFound extends BaseAbstractException {

	public LoanNotFound() {
		super(ExceptionStatus.LOAN_NOT_FOUND);
	}

}
