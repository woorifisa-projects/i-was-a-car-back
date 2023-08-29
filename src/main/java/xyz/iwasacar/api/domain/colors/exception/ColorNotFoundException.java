package xyz.iwasacar.api.domain.colors.exception;

import xyz.iwasacar.api.common.exception.base.BaseAbstractException;
import xyz.iwasacar.api.common.exception.base.ExceptionStatus;

public class ColorNotFoundException extends BaseAbstractException {

	public ColorNotFoundException() {
		super(ExceptionStatus.COLOR_NOT_FOUND);
	}

}
