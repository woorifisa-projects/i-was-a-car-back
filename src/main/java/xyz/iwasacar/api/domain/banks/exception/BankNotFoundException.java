package xyz.iwasacar.api.domain.banks.exception;

import xyz.iwasacar.api.common.exception.base.BaseAbstractException;
import xyz.iwasacar.api.common.exception.base.ExceptionStatus;

public class BankNotFoundException extends BaseAbstractException {

	public BankNotFoundException() {
		super(ExceptionStatus.BANK_NOT_FOUND);
	}

}
