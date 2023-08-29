package xyz.iwasacar.api.common.argumentresolver;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import xyz.iwasacar.api.common.annotation.Login;
import xyz.iwasacar.api.common.auth.jwt.JwtUtil;
import xyz.iwasacar.api.common.auth.jwt.MemberClaim;
import xyz.iwasacar.api.domain.members.exception.UnauthorizedException;
import xyz.iwasacar.api.domain.roles.entity.RoleName;

public class MemberClaimArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {

		// 파라미터의 어노테이션 정보 (@Login 이 붙어있나)
		boolean hasAnnotation = parameter.hasParameterAnnotation(Login.class);

		// 파라미터의 타입 정보 (MemberClaim 클래스인가)
		boolean isAssigned = MemberClaim.class.isAssignableFrom(parameter.getParameterType());

		return hasAnnotation && isAssigned;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		HttpServletRequest req = (HttpServletRequest)webRequest.getNativeRequest();
		Long memberId = Optional.ofNullable((Long)req.getAttribute(JwtUtil.AUTHORIZATION_ID))
			.orElseThrow(UnauthorizedException::new);
		List<RoleName> roles = Optional.ofNullable((List<RoleName>)req.getAttribute(JwtUtil.ROLES))
			.orElseThrow(UnauthorizedException::new);

		return new MemberClaim(memberId, roles);
	}

}
