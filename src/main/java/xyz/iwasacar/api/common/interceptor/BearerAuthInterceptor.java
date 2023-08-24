package xyz.iwasacar.api.common.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import xyz.iwasacar.api.common.auth.jwt.Jwt;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenParser;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenProvider;
import xyz.iwasacar.api.domain.members.exception.UnauthorizedException;

@Slf4j
@RequiredArgsConstructor
@Component
public class BearerAuthInterceptor implements HandlerInterceptor {

	private final JwtTokenParser jwtTokenParser;

	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		log.info("===============인터셉터 preHandle 호출=================");
		Cookie[] cookies = request.getCookies(); // [accessToken, jsessionID]
		Long memberId = 0L;
		String accessToken = "";

		if (cookies == null) {
			throw new IllegalArgumentException("유효하지 않은 토큰");
		}

		for (Cookie cookie : cookies) {

			if (!cookie.getName().equals("accessToken")) {
				continue;
			}

			accessToken = cookie.getValue();
			// accessToken(jwt) 만료 시간 검사
			if (!jwtTokenParser.isTokenExpired(accessToken)) {
				memberId = jwtTokenParser.getSubject(accessToken);
				request.setAttribute("Authorization", memberId);
				return true;
			}

			String refreshToken = (String)request.getSession().getAttribute("refreshToken");

			if (refreshToken == null) {
				throw new IllegalArgumentException();
			}

			if (jwtTokenParser.isTokenExpired(refreshToken)) {
				throw new UnauthorizedException();
			}
			memberId = jwtTokenParser.getSubject(refreshToken);
			Claims claims = jwtTokenParser.getClaims(refreshToken);

			Jwt newJwt = jwtTokenProvider.createJwt(claims, memberId);

			cookie.setValue(newJwt.getAccessToken());
			cookie.setHttpOnly(true);
			cookie.setSecure(false);
			cookie.setPath("/");
			cookie.setMaxAge((int)(jwtTokenProvider.getRefreshTokenExpireTimeMils() / 500));

			response.addCookie(cookie);
			request.getSession().setAttribute("refreshToken", newJwt.getRefreshToken());
			request.setAttribute("Authorization", memberId);
		}

		return true;
	}

}
