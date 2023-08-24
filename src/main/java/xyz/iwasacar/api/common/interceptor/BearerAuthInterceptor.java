package xyz.iwasacar.api.common.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenParser;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenProvider;

@Slf4j
@RequiredArgsConstructor
@Component
public class BearerAuthInterceptor implements HandlerInterceptor {

	private final JwtTokenParser jwtTokenParser;

	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public boolean preHandle(HttpServletRequest request,
		HttpServletResponse response,
		Object handler) {

		log.info("===============인터셉터 preHandle 호출=================");
		Cookie[] cookies = request.getCookies(); // [accessToken, jessionID]

		String accessToken = "";

		if (cookies == null) {
			throw new IllegalArgumentException("유효하지 않은 토큰");
		}

		for (Cookie cookie : cookies) {

			if (cookie.getName().equals("accessToken")) {

				accessToken = cookie.getValue();

				// accessToken(jwt) 만료 시간 검사
				if (jwtTokenParser.isTokenExpired(accessToken)) {
					log.info("============== 엑세스 토큰 만료됨! 엑세스 토큰 업데이트 시작=================");
					Long memberId = jwtTokenParser.getSubject(accessToken);
					Claims claims = jwtTokenParser.getClaims(accessToken);

					System.out.println("memberId : " + memberId + "claims :" + claims.toString());
					String refreshAccessToken = jwtTokenProvider.refreshAccessToken(claims, memberId);

					System.out.println(jwtTokenParser.checkExpiredTime(refreshAccessToken));

					Cookie updatedCookie = new Cookie("accessToken", refreshAccessToken);

					request.setAttribute("Authorization", memberId);
				}

			}
		}

		return true;
	}

}
