package xyz.iwasacar.api.common.interceptor;

import static xyz.iwasacar.api.common.auth.jwt.JwtUtil.*;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenParser;
import xyz.iwasacar.api.domain.members.exception.ForbiddenException;
import xyz.iwasacar.api.domain.roles.entity.RoleName;

@RequiredArgsConstructor
public class AdminInterceptor implements HandlerInterceptor {

	private final JwtTokenParser jwtTokenParser;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {

		List<RoleName> roles = (List<RoleName>)request.getAttribute(ROLES);

		for (RoleName role : roles) {
			if (Objects.equals(role, RoleName.ADMIN)) {
				return true;
			}
		}

		throw new ForbiddenException();
	}

}
