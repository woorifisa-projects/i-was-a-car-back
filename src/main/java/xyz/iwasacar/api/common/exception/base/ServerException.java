package xyz.iwasacar.api.common.exception.base;

public class ServerException extends BaseAbstractException {

	public ServerException() {
		super(ExceptionStatus.SERVER_ERROR);
	}

}
