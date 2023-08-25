package xyz.iwasacar.api.common.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import xyz.iwasacar.api.common.filter.Utf8EncodingFilter;
import xyz.iwasacar.api.common.filter.UuidContextFilter;

@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean<Utf8EncodingFilter> encodingFilter() {

		FilterRegistrationBean<Utf8EncodingFilter> filterRegistration = new FilterRegistrationBean<>(
			new Utf8EncodingFilter());

		filterRegistration.setOrder(1);
		filterRegistration.addUrlPatterns("/*");
		filterRegistration.setName("utf8EncodingFilter");

		return filterRegistration;
	}

	@Bean
	public FilterRegistrationBean<UuidContextFilter> uuidFilter() {

		FilterRegistrationBean<UuidContextFilter> filterRegistration = new FilterRegistrationBean<>(
			new UuidContextFilter());

		filterRegistration.setOrder(2);
		filterRegistration.addUrlPatterns("/*");
		filterRegistration.setName("uuidFilter");

		return filterRegistration;
	}

}
