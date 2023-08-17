package xyz.iwasacar.api.common.exception.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import xyz.iwasacar.api.common.dto.response.ErrorResponse;
import xyz.iwasacar.api.common.exception.base.BaseAbstractException;

/**
 * 전역 예외 처리
 *
 * @author dongyeol
 */
@Slf4j
@RestControllerAdvice
public class GlobalRestControllerAdvice {

	/**
	 * 사전 정의 예외 처리
	 *
	 * @param ex 발생한 예외
	 * @return 예외 메세지 + 예외 코드
	 * @author dongyeol
	 */
	@ExceptionHandler(BaseAbstractException.class)
	public ResponseEntity<ErrorResponse> foo(BaseAbstractException ex) {
		log.info("==== exception ====");
		log.error("", ex);
		return ErrorResponse.fail(ex.getExceptionStatus());
	}

}