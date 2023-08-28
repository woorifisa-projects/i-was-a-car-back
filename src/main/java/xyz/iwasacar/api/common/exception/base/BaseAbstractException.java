package xyz.iwasacar.api.common.exception.base;

import lombok.Getter;

@Getter
public abstract class BaseAbstractException extends RuntimeException {

	protected final ExceptionStatus exceptionStatus;

	protected BaseAbstractException(ExceptionStatus exceptionStatus) {
		super(exceptionStatus.getMessage());
		this.exceptionStatus = exceptionStatus;
	}

}
