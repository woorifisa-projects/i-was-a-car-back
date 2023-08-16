package xyz.iwasacar.api.common.auth.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {
	public static final byte[] secret = "CheolwonMinsuCheolwonMinsuCheolwonMinsuSecretKey".getBytes();
	private final Key key = Keys.hmacShaKeyFor(secret);

	public Jwt createJwt(Map<String, Object> claims) {
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
		long expireTimeMils = 1000 * 60 * 60;
		return new Date(System.currentTimeMillis() + expireTimeMils);
	}

	public Date getExpireDateRefreshToken() {
		long expireTimeMils = 1000L * 60 * 60 * 24 * 60;
		return new Date(System.currentTimeMillis() + expireTimeMils);
	}
}