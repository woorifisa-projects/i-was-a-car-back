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
import xyz.iwasacar.api.domain.histories.dto.response.PurchaseHistoryResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleResponse;
import xyz.iwasacar.api.domain.logs.entity.Log;
import xyz.iwasacar.api.domain.logs.repository.LogRepository;
import xyz.iwasacar.api.domain.members.entity.Member;
import xyz.iwasacar.api.domain.members.repository.MemberRepository;

@Aspect
@Component
@RequiredArgsConstructor
public class HistoryAspect {

	private final LogRepository logRepository;
	private final MemberRepository memberRepository;

	@Pointcut("execution(* xyz.iwasacar.api.domain.histories.controller.HistoryController.savePurchaseHistory(..))")
	private void purchaseMethod() {

	}

	@Pointcut("execution(* xyz.iwasacar.api.domain.histories.controller.SaleHistoryController.saveSalesHistory(..))")
	private void saleMethod() {

	}

	@Transactional
	@AfterReturning(value = "purchaseMethod()", returning = "response")
	public void publishPurchaseEvent(ResponseEntity<CommonResponse<PurchaseHistoryResponse>> response) {
		Long id = response.getBody().getData().getId();
		logRepository.save(Log.purchase(getMember(), id));
	}

	@Transactional
	@AfterReturning(value = "saleMethod()", returning = "response")
	public void publishSaleEvent(ResponseEntity<CommonResponse<SaleResponse>> response) {
		Long id = response.getBody().getData().getSaleId();
		logRepository.save(Log.sale(getMember(), id));
	}

	private Member getMember() {

		MemberClaim claim = MemberClaimContext.getClaim();
		return memberRepository.getBy(claim.getMemberId());
	}

}
