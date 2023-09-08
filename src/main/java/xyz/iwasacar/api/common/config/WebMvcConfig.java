package xyz.iwasacar.api.common.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import xyz.iwasacar.api.common.argumentresolver.MemberClaimArgumentResolver;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenParser;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenProvider;
import xyz.iwasacar.api.common.interceptor.AdminInterceptor;
import xyz.iwasacar.api.common.interceptor.BearerAuthInterceptor;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

	@Value("${client-url}")
	private String[] clientUrl;

	private final JwtTokenParser parser;
	private final JwtTokenProvider provider;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		// 테스트용으로 /api/v1/members 추가했는데 나중에 커밋할때는 지워야한다 log도 지워야함
		registry.addInterceptor(new BearerAuthInterceptor(parser, provider))
			.addPathPatterns("/api/v1/**")
			.excludePathPatterns("/api/v1/members/login", "/api/v1/members/signup", "/api/v1/products",
				"/api/v1/products/[0-9]+", "/api/v1/auth/email",
				"/api/v1/auth/email-confirm", "/api/v1/sales/**")
			.order(1);

		registry.addInterceptor(new AdminInterceptor(parser))
			.addPathPatterns("/api/v1/admin/**")
			.order(2);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new MemberClaimArgumentResolver());
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**")
			.allowedOrigins(clientUrl)
			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
			.allowedHeaders("Content-Type")
			.allowCredentials(true)
			.maxAge(3000);
	}

}
