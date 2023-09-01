package xyz.iwasacar.api.domain.roles.exception;

import xyz.iwasacar.api.common.exception.base.BaseAbstractException;
import xyz.iwasacar.api.common.exception.base.ExceptionStatus;

public class RoleNotFoundException extends BaseAbstractException {

	public RoleNotFoundException() {
		super(ExceptionStatus.ROLE_SERVER_ERROR);
	}

}
