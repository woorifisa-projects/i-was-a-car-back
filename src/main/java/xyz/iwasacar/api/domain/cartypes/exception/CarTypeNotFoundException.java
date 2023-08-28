package xyz.iwasacar.api.domain.cartypes.exception;

import xyz.iwasacar.api.common.exception.base.BaseAbstractException;
import xyz.iwasacar.api.common.exception.base.ExceptionStatus;

public class CarTypeNotFoundException extends BaseAbstractException {

	public CarTypeNotFoundException() {
		super(ExceptionStatus.CAR_TYPE_NOT_FOUND);
	}

}
