package xyz.iwasacar.api.domain.logs.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.domain.logs.entity.Log;
import xyz.iwasacar.api.domain.logs.repository.LogRepository;
import xyz.iwasacar.api.domain.members.dto.response.MemberResponse;
import xyz.iwasacar.api.domain.members.entity.Member;
import xyz.iwasacar.api.domain.members.exception.MemberNotFoundException;
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

	@Transactional
	@AfterReturning(value = "loginMethod()", returning = "memberResponseResponseEntity")
	public void publishLoginEvent(ResponseEntity<CommonResponse<MemberResponse>> memberResponseResponseEntity) {
		MemberResponse memberResponse = memberResponseResponseEntity.getBody().getData();

		Member member = memberRepository.findById(memberResponse.getId()).orElseThrow(MemberNotFoundException::new);
		member.updateLastLogin();

		logRepository.save(new Log(member));
	}

}
