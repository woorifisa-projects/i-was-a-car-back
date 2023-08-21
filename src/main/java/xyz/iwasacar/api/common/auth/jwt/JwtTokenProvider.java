package xyz.iwasacar.api.common.auth.jwt;

import java.security.Key;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
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

	public Jwt createJwt(Map<String, Object> claims, Long memberId) {
		log.info("[createJwt] Jwt 토큰 Response DTO 생성");
		String accessToken = createToken(claims, getExpireDateAccessToken(), memberId);
		String refreshToken = createToken(new HashMap<>(), getExpireDateRefreshToken(), memberId);
		return Jwt.builder()
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.build();
	}

	public String createToken(Map<String, Object> claims, Date expireDate, Long memberId) {

		long curTime = System.currentTimeMillis();

		return Jwts.builder()
			.setSubject(String.valueOf(memberId)) // memberId
			.setClaims(claims) // 역할이 들어갈 예정
			.setExpiration(expireDate) // 만료 날짜
			.setIssuedAt(new Date(curTime)) // 발행 날짜
			.signWith(key)
			.compact();
	}

	private Claims getClaims(String token) {
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