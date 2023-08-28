package xyz.iwasacar.api.domain.brands.exception;

import xyz.iwasacar.api.common.exception.base.BaseAbstractException;
import xyz.iwasacar.api.common.exception.base.ExceptionStatus;

public class BrandNotFoundException extends BaseAbstractException {

	public BrandNotFoundException() {
		super(ExceptionStatus.BRAND_NOT_FOUND);
	}

}
