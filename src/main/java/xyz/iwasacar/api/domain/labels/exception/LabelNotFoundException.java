package xyz.iwasacar.api.domain.labels.exception;

import xyz.iwasacar.api.common.exception.base.BaseAbstractException;
import xyz.iwasacar.api.common.exception.base.ExceptionStatus;

public class LabelNotFoundException extends BaseAbstractException {

	public LabelNotFoundException() {
		super(ExceptionStatus.LABEL_NOT_FOUND);
	}

}
