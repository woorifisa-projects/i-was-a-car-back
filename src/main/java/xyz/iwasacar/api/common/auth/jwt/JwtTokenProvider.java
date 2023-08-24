package xyz.iwasacar.api.common.auth.jwt;

import java.security.Key;
import java.sql.Date;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Component
public class JwtTokenProvider {

	private final byte[] secretKey;

	private final long accessTokenExpireTimeMils;

	private final long refreshTokenExpireTimeMils;

	private final Key key;

	public JwtTokenProvider(
		@Value("${jwt.secret-key}") final String secret,
		@Value("${jwt.accesstoken-expire-time-mils}") final long accessTokenExpireTimeMils,
		@Value("${jwt.refreshtoken-expire-time-mils}") final long refreshTokenExpireTimeMils
	) {
		this.secretKey = secret.getBytes();
		this.accessTokenExpireTimeMils = accessTokenExpireTimeMils;
		this.refreshTokenExpireTimeMils = refreshTokenExpireTimeMils;
		this.key = Keys.hmacShaKeyFor(secretKey);
	}

	@PostConstruct
	public void postConstruct() {
		log.info("AccessTokenExpireTimeMils = {}", accessTokenExpireTimeMils);
		log.info("RefreshTokenExpireTimeMils = {}", refreshTokenExpireTimeMils);
	}

	public Jwt createJwt(final Map<String, Object> claims, final Long memberId) {
		log.info("[createJwt] Jwt 토큰 Response DTO 생성");
		String accessToken = createToken(claims, getExpireDateAccessToken(), memberId);
		String refreshToken = createToken(claims, getExpireDateRefreshToken(), memberId);
		return Jwt.builder()
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.build();
	}

	public String createToken(final Map<String, Object> claims, final Date expireDate, final Long memberId) {

		long curTime = System.currentTimeMillis();

		return Jwts.builder()
			.setSubject(String.valueOf(memberId)) // memberId
			.setClaims(claims)
			.setExpiration(expireDate)
			.setIssuedAt(new Date(curTime))
			.signWith(key)
			.compact();
	}

	private Date getExpireDateAccessToken() {
		return new Date(System.currentTimeMillis() + accessTokenExpireTimeMils);
	}

	private Date getExpireDateRefreshToken() {
		return new Date(System.currentTimeMillis() + refreshTokenExpireTimeMils);
	}

}
