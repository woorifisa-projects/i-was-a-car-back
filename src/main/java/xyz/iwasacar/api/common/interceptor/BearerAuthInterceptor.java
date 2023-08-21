package xyz.iwasacar.api.common.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenParser;

@Slf4j
@RequiredArgsConstructor
@Component
public class BearerAuthInterceptor implements HandlerInterceptor {

	private final JwtTokenParser jwtTokenParser;

	@Override
	public boolean preHandle(HttpServletRequest request,
		HttpServletResponse response,
		Object handler) {

		log.info("===============인터셉터 preHandle 호출=================");
		Cookie[] cookie = request.getCookies();

		String token = "";

		if (cookie == null) {
			throw new IllegalArgumentException("유효하지 않은 토큰");
		} else {
			token = cookie[0].getValue();
			String memberId = jwtTokenParser.getSubject(token);
			request.setAttribute("Authorization", memberId);
		}

		return true;
	}

}
