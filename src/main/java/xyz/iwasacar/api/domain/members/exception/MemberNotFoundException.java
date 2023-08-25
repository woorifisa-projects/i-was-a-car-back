package xyz.iwasacar.api.domain.members.exception;

import xyz.iwasacar.api.common.exception.base.BaseAbstractException;
import xyz.iwasacar.api.common.exception.base.ExceptionStatus;

public class MemberNotFoundException extends BaseAbstractException {

	public MemberNotFoundException() {
		super(ExceptionStatus.MEMBER_NOT_FOUND);
	}

}
