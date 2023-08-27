package xyz.iwasacar.api.domain.members.exception.ForbiddenException;

import xyz.iwasacar.api.common.exception.base.BaseAbstractException;
import xyz.iwasacar.api.common.exception.base.ExceptionStatus;

public class ForbiddenException extends BaseAbstractException {

	public ForbiddenException(ExceptionStatus exceptionStatus) {
		super(exceptionStatus);
	}

}
