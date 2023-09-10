package xyz.iwasacar.api.common.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import xyz.iwasacar.api.common.context.UuidContext;
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
	public ResponseEntity<ErrorResponse> handleBaseException(BaseAbstractException ex) {
		log.error("[{}] {} {}", UuidContext.getUuid(), ex.getExceptionStatus().getCode(), ex.getMessage());
		log.error("", ex);

		return ErrorResponse.fail(ex.getExceptionStatus());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidateException(MethodArgumentNotValidException ex) {
		
		String defaultMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();

		return ErrorResponse.validFail(defaultMessage);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
		ErrorResponse response = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());

		log.error("[{}] {}", UuidContext.getUuid(), ex.getMessage());
		log.error("", ex);

		return ResponseEntity
			.internalServerError()
			.body(response);
	}

}
