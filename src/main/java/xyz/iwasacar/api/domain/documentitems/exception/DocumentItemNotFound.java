package xyz.iwasacar.api.domain.documentitems.exception;

import xyz.iwasacar.api.common.exception.base.BaseAbstractException;
import xyz.iwasacar.api.common.exception.base.ExceptionStatus;

public class DocumentItemNotFound extends BaseAbstractException {

	public DocumentItemNotFound() {
		super(ExceptionStatus.DOCUMENT_ITEM_NOT_FOUND);
	}

}
