package xyz.iwasacar.api.common.exception.base;

import lombok.Getter;

@Getter
public class BaseAbstractException extends RuntimeException {

	protected final ExceptionStatus exceptionStatus;

	public BaseAbstractException(ExceptionStatus exceptionStatus) {
		super(exceptionStatus.getMessage());
		this.exceptionStatus = exceptionStatus;
	}

}
