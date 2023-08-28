package xyz.iwasacar.api.common.exception.base;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionStatus {

	PRODUCT_NOT_FOUNT(404101, "해당 상품을 찾을 수 없습니다.", NOT_FOUND),

	MEMBER_NOT_FOUND(404, "멤버를 찾을 수 없습니다.", NOT_FOUND),
	LOGIN_FAIL(401, "", UNAUTHORIZED),
	AUTHORIZE_FORBIDDEN(403, "", FORBIDDEN),

	POST_NOT_FOUND(404, "", NOT_FOUND);

	private final int code;
	private final String message;
	private final HttpStatus httpStatus;

}
