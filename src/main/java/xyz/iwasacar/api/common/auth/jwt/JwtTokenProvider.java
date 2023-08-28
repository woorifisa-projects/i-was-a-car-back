package xyz.iwasacar.api.common.auth.jwt;

import static xyz.iwasacar.api.common.auth.jwt.JwtUtil.*;

import java.security.Key;
import java.sql.Date;
import java.util.Map;

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

	public JwtDto createJwt(final MemberClaim memberClaim) {

		String accessToken = createToken(memberClaim, getExpireDateAccessToken());
		String refreshToken = createToken(memberClaim, getExpireDateRefreshToken());

		return JwtDto.builder()
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.build();
	}

	// public String createToken(final Map<String, Object> claims, final Date expireDate, final Long memberId) {
	//
	// 	long curTime = System.currentTimeMillis();
	//
	// 	return Jwts.builder()
	// 		.setClaims(claims)
	// 		.setSubject(String.valueOf(memberId)) // memberId
	// 		.setExpiration(expireDate)
	// 		.setIssuedAt(new Date(curTime))
	// 		.signWith(key)
	// 		.compact();
	// }

	private String createToken(final MemberClaim memberClaim, final Date expireDate) {

		long curTime = System.currentTimeMillis();
		Map<String, Object> claims = Map.of(
			AUTHORIZATION_ID, memberClaim.getMemberId(),
			ROLES, memberClaim.getRoles()
		);

		return Jwts.builder()
			.setClaims(claims)
			.setSubject(SUBJECT) // memberId
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
