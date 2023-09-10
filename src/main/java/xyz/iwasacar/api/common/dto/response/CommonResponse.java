package xyz.iwasacar.api.common.dto.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CommonResponse<T> {

	private final int code;
	private final T data;

	public static <T> ResponseEntity<CommonResponse<T>> success(HttpStatus status, int code, T data) {

		return ResponseEntity
			.status(status)
			.contentType(MediaType.APPLICATION_JSON)
			.body(new CommonResponse<>(code, data));
	}


	public static <T> ResponseEntity<CommonResponse<T>> success(HttpHeaders headers, HttpStatus status,
		int code, T data) {

		return ResponseEntity
			.status(status)
			.headers(headers)
			.body(new CommonResponse<>(code, data));
	}

}
