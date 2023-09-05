package xyz.iwasacar.api.common.auth.email.exception;

import static xyz.iwasacar.api.common.exception.base.ExceptionStatus.*;

import xyz.iwasacar.api.common.exception.base.BaseAbstractException;

public class EmailCodeNotFoundException extends BaseAbstractException {

	public EmailCodeNotFoundException() {
		super(EMAIL_CODE_NOT_FOUND);
	}
}
