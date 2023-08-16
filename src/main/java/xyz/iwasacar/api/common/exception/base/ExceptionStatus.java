package xyz.iwasacar.api.common.exception.base;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionStatus {

	MEMBER_NOT_FOUND(404, "", NOT_FOUND),
	LOGIN_FAIL(401, "", UNAUTHORIZED),
	FORBIDDEN(403, "", HttpStatus.FORBIDDEN),

	POST_NOT_FOUND(404, "", NOT_FOUND);

	private final int code;
	private final String message;
	private final HttpStatus httpStatus;

}
