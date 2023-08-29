package xyz.iwasacar.api.domain.caroptions.exception;

import xyz.iwasacar.api.common.exception.base.BaseAbstractException;
import xyz.iwasacar.api.common.exception.base.ExceptionStatus;

public class CarOptionException extends BaseAbstractException {

	public CarOptionException() {
		super(ExceptionStatus.CAR_OPTION_OMIT);
	}

}
