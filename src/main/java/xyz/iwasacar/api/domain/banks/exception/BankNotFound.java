package xyz.iwasacar.api.domain.banks.exception;

import xyz.iwasacar.api.common.exception.base.BaseAbstractException;
import xyz.iwasacar.api.common.exception.base.ExceptionStatus;

public class BankNotFound extends BaseAbstractException {

	public BankNotFound() {
		super(ExceptionStatus.BANK_NOT_FOUND);
	}

}
