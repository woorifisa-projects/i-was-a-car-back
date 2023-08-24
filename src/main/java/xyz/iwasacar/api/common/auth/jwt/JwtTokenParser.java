package xyz.iwasacar.api.common.auth.jwt;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

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

	public long checkExpiredTime(final String token) {
		return getClaims(token)
			.getExpiration()
			.getTime();
	}

	public boolean isTokenExpired(final String token) {
		long expirationTimestamp = getClaims(token)
			.getExpiration()
			.getTime();
		System.out.println("원래 만료시간은 " + expirationTimestamp + " 입니다.");

		long currentTimestamp = System.currentTimeMillis();

		return currentTimestamp > expirationTimestamp;
	}
}
