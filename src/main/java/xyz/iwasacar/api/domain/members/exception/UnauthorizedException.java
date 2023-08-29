package xyz.iwasacar.api.domain.members.exception;

import static xyz.iwasacar.api.common.exception.base.ExceptionStatus.*;

import xyz.iwasacar.api.common.exception.base.BaseAbstractException;

public class UnauthorizedException extends BaseAbstractException {

	public UnauthorizedException() {
		super(LOGIN_FAIL);
	}

}
