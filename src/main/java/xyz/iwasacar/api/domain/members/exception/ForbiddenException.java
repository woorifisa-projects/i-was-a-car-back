package xyz.iwasacar.api.domain.members.exception;

import xyz.iwasacar.api.common.exception.base.BaseAbstractException;
import xyz.iwasacar.api.common.exception.base.ExceptionStatus;

public class ForbiddenException extends BaseAbstractException {

	public ForbiddenException() {
		super(ExceptionStatus.AUTHORIZE_FORBIDDEN);
	}

}
