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
	FORBIDDEN(403, "권한이 없어 접근이 불가능합니다.", HttpStatus.FORBIDDEN),

	POST_NOT_FOUND(404, "", NOT_FOUND),

	ROLE_SERVER_ERROR(500, "잘못된 권한으로 인해 서버에 오류가 발생했습니다.", INTERNAL_SERVER_ERROR),
	SERVER_ERROR(500, "서버에 오류가 발생했습니다.", INTERNAL_SERVER_ERROR);

	private final int code;
	private final String message;
	private final HttpStatus httpStatus;

}
