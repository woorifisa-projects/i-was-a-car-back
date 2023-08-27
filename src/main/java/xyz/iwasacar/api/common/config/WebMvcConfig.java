package xyz.iwasacar.api.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenParser;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenProvider;
import xyz.iwasacar.api.common.interceptor.AdminInterceptor;
import xyz.iwasacar.api.common.interceptor.BearerAuthInterceptor;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

	private final JwtTokenParser parser;
	private final JwtTokenProvider provider;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new BearerAuthInterceptor(parser, provider))
			.addPathPatterns("/api/v1/**")
			.excludePathPatterns("/api/v1/members/login", "/api/v1/members/signup", "/api/v1/products")
			.order(1);

		registry.addInterceptor(new AdminInterceptor(parser))
			.addPathPatterns("/api/v1/**")
			.excludePathPatterns("/api/v1/members/login", "/api/v1/members/signup", "/api/v1/products")
			.order(2);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:3000") // 허용할 출처
			.allowedMethods("*") // 허용할 HTTP method
			.allowCredentials(true) // 쿠키 인증 요청 허용
			.maxAge(3000); // 원하는 시간만큼 pre-flight 리퀘스트를 캐싱
	}

}
