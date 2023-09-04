package xyz.iwasacar.api.common.config;

import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.MediaType.*;

import org.springframework.context.annotation.Bean;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import xyz.iwasacar.api.common.exception.base.ServerException;

@Slf4j
public class FeignClientBasicConfig {

	public static final String UTF_8 = APPLICATION_FORM_URLENCODED_VALUE + ";charset=utf-8";

	@Bean
	public RequestInterceptor requestInterceptor() {
		return requestTemplate -> requestTemplate.header(CONTENT_TYPE, UTF_8);
	}

	@Bean
	public ErrorDecoder decoder() {

		return (methodKey, response) -> {
			log.error("{} 요청이 성공하지 못했습니다. requestUrl: {}", methodKey, response);

			return new ServerException();
		};
	}

}
