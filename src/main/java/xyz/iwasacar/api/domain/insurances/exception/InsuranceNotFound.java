package xyz.iwasacar.api.domain.insurances.exception;

import xyz.iwasacar.api.common.exception.base.BaseAbstractException;
import xyz.iwasacar.api.common.exception.base.ExceptionStatus;

public class InsuranceNotFound extends BaseAbstractException {

	public InsuranceNotFound() {
		super(ExceptionStatus.INSURANCE_NOT_FOUND);
	}

}
