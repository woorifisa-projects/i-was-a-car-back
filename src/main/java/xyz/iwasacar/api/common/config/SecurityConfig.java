package xyz.iwasacar.api.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import xyz.iwasacar.api.common.component.BcryptPasswordEncoder;
import xyz.iwasacar.api.common.component.PasswordEncoder;

@Configuration
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BcryptPasswordEncoder();
	}

}
