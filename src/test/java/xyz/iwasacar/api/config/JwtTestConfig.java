package xyz.iwasacar.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import xyz.iwasacar.api.common.auth.jwt.JwtTokenParser;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenProvider;

@TestConfiguration
public class JwtTestConfig {

	// @Autowired
	private final JwtTokenProvider jwtTokenProvider;

	// @Autowired
	private final JwtTokenParser jwtTokenParser;

	public JwtTestConfig(
		@Value("${jwt.secret-key}") final String secret,
		@Value("${jwt.accesstoken-expire-time-mils}") final long accessTokenExpireTimeMils,
		@Value("${jwt.refreshtoken-expire-time-mils}") final long refreshTokenExpireTimeMils
	) {
		this.jwtTokenProvider = new JwtTokenProvider(secret, accessTokenExpireTimeMils, refreshTokenExpireTimeMils);
		this.jwtTokenParser = new JwtTokenParser(secret);
	}

	@Bean
	public JwtTokenProvider jwtTokenProvider() {
		return jwtTokenProvider;
	}

	@Bean
	public JwtTokenParser jwtTokenParser() {
		return jwtTokenParser;
	}

}
