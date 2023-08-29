package xyz.iwasacar.api.common.interceptor;

import static xyz.iwasacar.api.common.auth.jwt.JwtUtil.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import xyz.iwasacar.api.common.auth.jwt.JwtDto;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenParser;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenProvider;
import xyz.iwasacar.api.common.auth.jwt.MemberClaim;
import xyz.iwasacar.api.domain.members.exception.UnauthorizedException;

@Slf4j
@RequiredArgsConstructor
public class BearerAuthInterceptor implements HandlerInterceptor {

	private final JwtTokenParser jwtTokenParser;
	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		Cookie[] cookies = request.getCookies(); // [accessToken, jsessionID]

		if (cookies == null) {
			throw new UnauthorizedException();
		}

		for (Cookie cookie : cookies) {

			if (!cookie.getName().equals(ACCESS_TOKEN)) {
				continue;
			}

			String accessToken = cookie.getValue();

			// accessToken(jwt) 만료 시간 검사
			if (!isInvalidToken(accessToken)) {
				Long memberId = jwtTokenParser.getMemberClaims(accessToken)
					.getMemberId();
				request.setAttribute(AUTHORIZATION_ID, memberId);
				return true;
			}

			String refreshToken = (String)request.getSession().getAttribute(REFRESH_TOKEN);

			if (isInvalidToken(refreshToken)) {
				throw new UnauthorizedException();
			}

			MemberClaim memberClaims = jwtTokenParser.getMemberClaims(accessToken);
			JwtDto newJwtDto = jwtTokenProvider.createJwt(memberClaims);

			refreshCookie(cookie, accessToken);

			response.addCookie(cookie);
			request.getSession().setAttribute(REFRESH_TOKEN, newJwtDto.getRefreshToken());
			request.setAttribute(AUTHORIZATION_ID, memberClaims.getMemberId());
			request.setAttribute(ROLES, memberClaims.getRoles());
		}

		return true;
	}

	private void refreshCookie(final Cookie cookie, final String accessToken) {
		cookie.setValue(accessToken);
		cookie.setHttpOnly(true);
		cookie.setSecure(false);
		cookie.setPath("/");
		cookie.setMaxAge((int)(jwtTokenProvider.getRefreshTokenExpireTimeMils() / 500));
	}

	private boolean isInvalidToken(final String token) {
		return token == null || jwtTokenParser.isTokenExpired(token);
	}

}