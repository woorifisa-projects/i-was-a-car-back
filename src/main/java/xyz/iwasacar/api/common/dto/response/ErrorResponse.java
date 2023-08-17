package xyz.iwasacar.api.common.dto.response;

import org.springframework.http.ResponseEntity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.exception.base.ExceptionStatus;

@RequiredArgsConstructor
@Getter
public class ErrorResponse {

	private final int code;
	private final String message;

	public static ResponseEntity<ErrorResponse> fail(ExceptionStatus status) {
		return ResponseEntity.status(status.getHttpStatus())
			.body(new ErrorResponse(status.getCode(), status.getMessage()));
	}

}
