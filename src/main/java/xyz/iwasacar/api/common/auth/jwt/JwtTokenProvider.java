package xyz.iwasacar.api.common.auth.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {
	private byte[] secretKey;

	private long accessTokenExpireTimeMils;

	private long refreshTokenExpireTimeMils;

	private final Key key;

	public JwtTokenProvider(
		@Value("${jwt.secret-key}") String secret,
		@Value("${jwt.accesstoken-expire-time-mils}") long accessTokenExpireTimeMils,
		@Value("${jwt.refreshtoken-expire-time-mils}") long refreshTokenExpireTimeMils
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

	public Jwt createJwt(Map<String, Object> claims) {
		log.info("[createJwt] Jwt 토큰 Response DTO 생성");
		String accessToken = createToken(claims, getExpireDateAccessToken());
		String refreshToken = createToken(new HashMap<>(), getExpireDateRefreshToken());
		return Jwt.builder()
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.build();
	}

	public String createToken(Map<String, Object> claims, Date expireDate) {
		return Jwts.builder()
			.setClaims(claims)
			.setExpiration(expireDate)
			.signWith(key)
			.compact();
	}

	public Claims getClaims(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

	public Date getExpireDateAccessToken() {
		return new Date(System.currentTimeMillis() + accessTokenExpireTimeMils);
	}

	public Date getExpireDateRefreshToken() {
		return new Date(System.currentTimeMillis() + refreshTokenExpireTimeMils);
	}
}