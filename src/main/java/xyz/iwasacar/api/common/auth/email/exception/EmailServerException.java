package xyz.iwasacar.api.common.auth.email.exception;

import static xyz.iwasacar.api.common.exception.base.ExceptionStatus.*;

import xyz.iwasacar.api.common.exception.base.BaseAbstractException;

public class EmailServerException extends BaseAbstractException {
	public EmailServerException() {
		super(EMAIL_SERVER_ERROR);
	}
}
