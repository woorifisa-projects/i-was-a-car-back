package xyz.iwasacar.api.common.auth.email.exception;

import static xyz.iwasacar.api.common.exception.base.ExceptionStatus.*;

import xyz.iwasacar.api.common.exception.base.BaseAbstractException;

public class InvalidEmailVerificationException extends BaseAbstractException {
	public InvalidEmailVerificationException() {
		super(EMAIL_AUTHENTICATION_FAIL);
	}
}
