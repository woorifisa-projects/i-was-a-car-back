package xyz.iwasacar.api.common.interceptor;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import xyz.iwasacar.api.common.context.MemberClaimContext;
import xyz.iwasacar.api.domain.roles.entity.RoleName;

public class AdminInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {

		if (CorsUtils.isPreFlightRequest(request)) {
			return true;
		}

		List<RoleName> roles = MemberClaimContext.getClaim().getRoles();

		for (RoleName role : roles) {
			if (Objects.equals(role, RoleName.ADMIN)) {
				return true;
			}
		}

		response.sendError(401, "권한이 없습니다.");
		return false;
	}

}
