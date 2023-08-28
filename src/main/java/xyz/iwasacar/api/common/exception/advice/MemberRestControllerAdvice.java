package xyz.iwasacar.api.common.exception.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import xyz.iwasacar.api.common.dto.response.ErrorResponse;
import xyz.iwasacar.api.domain.members.exception.UnauthorizedException;

@RestControllerAdvice
public class MemberRestControllerAdvice {

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ErrorResponse> handleLoginFailException(UnauthorizedException e) {
		return ErrorResponse.fail(e.getExceptionStatus());
	}

}
