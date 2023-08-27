package xyz.iwasacar.api.domain.histories.exception;

import static xyz.iwasacar.api.common.exception.base.ExceptionStatus.*;

import xyz.iwasacar.api.common.exception.base.BaseAbstractException;

public class HistoryNotFound extends BaseAbstractException {

	public HistoryNotFound() {
		super(HISTORY_NOT_FOUND);
	}
}
