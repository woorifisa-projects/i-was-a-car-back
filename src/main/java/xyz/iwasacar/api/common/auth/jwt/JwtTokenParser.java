package xyz.iwasacar.api.common.auth.jwt;

import static java.util.stream.Collectors.*;
import static xyz.iwasacar.api.common.auth.jwt.JwtUtil.*;

import java.security.Key;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import xyz.iwasacar.api.domain.members.exception.UnauthorizedException;
import xyz.iwasacar.api.domain.roles.entity.RoleName;

@Slf4j
@Component
public class JwtTokenParser {

	private final Key key;

	public JwtTokenParser(@Value("${jwt.secret-key}") final String secret) {
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
	}

	// Claim을 그냥 반환하지말고 필요한 정보만 모아서 객체로 반환할 수 있도록.
	private Claims getClaims(final String token) {
		return Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

	public MemberClaim getMemberClaims(final String token) {
		try {
			Claims claims = getClaims(token);
			Long memberId = claims.get(AUTHORIZATION_ID, Long.class);
			List<RoleName> roles = ((List<String>)claims.get(ROLES, List.class))
				.stream()
				.map(RoleName::valueOf)
				.collect(toList());

			return new MemberClaim(memberId, roles);
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
	}

	public boolean isTokenExpired(final String token) {
		try {

			getClaims(token).getExpiration();
			return false;

		} catch (ExpiredJwtException e) {
			log.error("", e);
			return true;
		} catch (Exception e) {
			log.error("", e);
			return true;
		}
	}
}
