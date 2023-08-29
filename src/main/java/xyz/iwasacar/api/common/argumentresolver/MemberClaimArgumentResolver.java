package xyz.iwasacar.api.common.argumentresolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import xyz.iwasacar.api.common.annotation.Login;
import xyz.iwasacar.api.common.auth.jwt.MemberClaim;
import xyz.iwasacar.api.common.context.MemberClaimContext;

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

		return MemberClaimContext.getClaim();
	}

}
