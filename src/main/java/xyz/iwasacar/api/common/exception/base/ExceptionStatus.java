package xyz.iwasacar.api.common.exception.base;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionStatus {
	LOGIN_FAIL(401, "", UNAUTHORIZED),

	AUTHORIZE_FORBIDDEN(403, "", FORBIDDEN),

	PRODUCT_NOT_FOUND(404201, "해당 상품을 찾을 수 없습니다.", NOT_FOUND),
	MEMBER_NOT_FOUND(404101, "회원을 찾을 수 없습니다.", NOT_FOUND),
	CAR_TYPE_NOT_FOUND(404301, "차종을 찾을 수 없습니다.", NOT_FOUND),
	BRAND_NOT_FOUND(404401, "제조사를 찾을 수 없습니다.", NOT_FOUND),
	COLOR_NOT_FOUND(404501, "색을 찾을 수 없습니다.", NOT_FOUND),
	LABEL_NOT_FOUND(404601, "라벨을 찾을 수 없습니다.", NOT_FOUND),
	BANK_NOT_FOUND(404701, "은행을 찾을 수 없습니다.", NOT_FOUND),
	CAR_OPTION_OMIT(404801, "잘못 입력된 옵션이 있습니다.", NOT_FOUND),

	ROLE_SERVER_ERROR(500, "잘못된 권한으로 인해 서버에 오류가 발생했습니다.", INTERNAL_SERVER_ERROR),

	FILE_CONVERT_FAIL(500301, "파일 변환을 실패했습니다.", INTERNAL_SERVER_ERROR),

	SERVER_ERROR(500, "서버에 오류가 발생했습니다.", INTERNAL_SERVER_ERROR);

	private final int code;
	private final String message;
	private final HttpStatus httpStatus;

}
