package xyz.iwasacar.api.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;
import xyz.iwasacar.api.common.interceptor.BearerAuthInterceptor;

@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private final BearerAuthInterceptor bearerAuthInterceptor;

	public WebMvcConfig(BearerAuthInterceptor bearerAuthInterceptor) {
		this.bearerAuthInterceptor = bearerAuthInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		log.info("==================인터셉터 등록됨==================");
		registry.addInterceptor(bearerAuthInterceptor)
			.addPathPatterns("/api/v1/**")
			.excludePathPatterns("/api/v1/members/login", "/api/v1/members/signup");
	}

}
