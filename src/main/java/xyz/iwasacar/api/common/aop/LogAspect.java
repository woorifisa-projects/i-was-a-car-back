package xyz.iwasacar.api.common.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;
import xyz.iwasacar.api.common.context.UuidContext;

@Slf4j
@Aspect
@Component
public class LogAspect {

	@Pointcut("execution(* xyz.iwasacar.api.domain.*.controller.*.*(..))")
	private void loggingPointcut() {
	}

	@Around("loggingPointcut()")
	public Object logging(ProceedingJoinPoint pjp) throws Throwable {

		String destination = getRequest().getRequestURI();
		String id = UuidContext.getUuid();

		log.info("ENTER [{}] {}", id, destination);
		Object proceed = pjp.proceed();
		log.info("QUIT [{}] {}", id, destination);

		return proceed;
	}

	@AfterThrowing(value = "loggingPointcut()", throwing = "ex")
	public void afterThrowing(Exception ex) {
		String destination = getRequest().getRequestURI();
		String id = UuidContext.getUuid();
		log.error("EXCEPTION [{}] {} {}", id, destination, ex.getMessage());
		log.error("", ex);
	}

	private HttpServletRequest getRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
	}

}
