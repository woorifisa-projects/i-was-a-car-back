package xyz.iwasacar.api.domain.logs.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.auth.jwt.MemberClaim;
import xyz.iwasacar.api.common.context.MemberClaimContext;
import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.domain.logs.entity.Log;
import xyz.iwasacar.api.domain.logs.repository.LogRepository;
import xyz.iwasacar.api.domain.members.dto.response.MemberResponse;
import xyz.iwasacar.api.domain.members.entity.Member;
import xyz.iwasacar.api.domain.members.repository.MemberRepository;

@Aspect
@Component
@RequiredArgsConstructor
public class LoginLogAspect {

	private final LogRepository logRepository;
	private final MemberRepository memberRepository;

	@Pointcut("execution(* xyz.iwasacar.api.domain.members.controller.MemberController.login(..))")
	private void loginMethod() {

	}

	@Pointcut("execution(* xyz.iwasacar.api.domain.members.controller.MemberController.logout(..))")
	private void logoutMethod() {

	}

	@Pointcut("execution(* xyz.iwasacar.api.domain.members.controller.MemberController.updateMember(..))")
	private void updateMethod() {

	}

	@Transactional
	@AfterReturning(value = "loginMethod()", returning = "response")
	public void publishLoginEvent(ResponseEntity<CommonResponse<MemberResponse>> response) {
		Long id = response.getBody().getData().getId();
		Member member = memberRepository.getBy(id);
		logRepository.save(Log.login(member));
	}

	@Transactional
	@AfterReturning(value = "logoutMethod()")
	public void publishLogoutEvent() {

		logRepository.save(Log.logout(getMember()));
	}

	@Transactional
	@AfterReturning(value = "updateMethod()")
	public void publishUpdateEvent() {

		logRepository.save(Log.update(getMember()));
	}

	private Member getMember() {

		MemberClaim claim = MemberClaimContext.getClaim();
		return memberRepository.getBy(claim.getMemberId());
	}

}
