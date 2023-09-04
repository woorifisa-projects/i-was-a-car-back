package xyz.iwasacar.api.common.auth.email.exception;

import static xyz.iwasacar.api.common.exception.base.ExceptionStatus.*;

import xyz.iwasacar.api.common.exception.base.BaseAbstractException;

public class InvalidEmailCodeException extends BaseAbstractException {

	public InvalidEmailCodeException() {
		super(EMAIL_CODE_NOT_FOUND);
	}
}
