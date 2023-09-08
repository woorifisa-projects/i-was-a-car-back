package xyz.iwasacar.api.domain.histories.exception;

import xyz.iwasacar.api.common.exception.base.BaseAbstractException;
import xyz.iwasacar.api.common.exception.base.ExceptionStatus;

public class SaleHistoryNotFoundException extends BaseAbstractException {
	public SaleHistoryNotFoundException() {
		super(ExceptionStatus.SALE_NOT_FOUND);
	}
}
