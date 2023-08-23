package xyz.iwasacar.api.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Value("${client-url}")
	private String clientUrl;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		System.out.println(clientUrl);
		registry.addMapping("/**")
			.allowedOrigins(clientUrl)
			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
			.allowCredentials(true)
			.maxAge(3000);
	}

}
