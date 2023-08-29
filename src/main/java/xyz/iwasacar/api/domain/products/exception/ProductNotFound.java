package xyz.iwasacar.api.domain.products.exception;

import xyz.iwasacar.api.common.exception.base.BaseAbstractException;
import xyz.iwasacar.api.common.exception.base.ExceptionStatus;

public class ProductNotFound extends BaseAbstractException {

	public ProductNotFound() {
		super(ExceptionStatus.PRODUCT_NOT_FOUND);
	}

}
