package xyz.iwasacar.api.common.auth.jwt;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenParser {

	private final byte[] secretKey;

	private final Key key;

	public JwtTokenParser(
		@Value("${jwt.secret-key}") final String secret
	) {
		this.secretKey = secret.getBytes();
		this.key = Keys.hmacShaKeyFor(secretKey);
	}

	// Claim을 그냥 반환하지말고 필요한 정보만 모아서 객체로 반환할 수 있도록.
	public Claims getClaims(final String token) {
		return Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

	public Long getSubject(final String token) {
		return Long.valueOf((String.valueOf(getClaims(token).get("memberId"))));
	}

	public boolean isTokenExpired(final String token) {
		try {
			long expirationTimestamp = getClaims(token)
				.getExpiration()
				.getTime();

			System.out.println("원래 만료시간은 " + expirationTimestamp + " 입니다.");

			long currentTimestamp = System.currentTimeMillis();
			System.out.println("현재 타임스탬프 ? " + currentTimestamp);
			return false;
		} catch (ExpiredJwtException e) {
			return true;
		}
	}
}
