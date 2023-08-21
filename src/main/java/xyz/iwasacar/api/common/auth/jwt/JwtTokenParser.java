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
		@Value("${jwt.secret-key}") String secret
	) {
		this.secretKey = secret.getBytes();
		this.key = Keys.hmacShaKeyFor(secretKey);
	}

	private Claims getClaims(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

	public String getSubject(String token) {
		return String.valueOf(getClaims(token).get("memberId"));
	}

}
