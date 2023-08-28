package xyz.iwasacar.api.domain.histories.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.histories.entity.PurchaseHistory;
import xyz.iwasacar.api.domain.insurances.entity.Insurance;
import xyz.iwasacar.api.domain.labels.entity.LabelName;
import xyz.iwasacar.api.domain.loans.entity.Loan;
import xyz.iwasacar.api.domain.members.entity.Member;
import xyz.iwasacar.api.domain.products.entity.Product;

@RequiredArgsConstructor
@Getter
public class PurchaseHistoryDetailResponse {
	//반환해야할 값
	// 주문번호, 주문일자,회원이름, 연락처, 주소, 배송예정, 상품이름, 상품가격, 대출 이름, 보험 이름, 라벨이름

	private final Long purchaseHistoryNo;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private final LocalDateTime createdAt;

	private final LocalDateTime delivery_schehdule;

	private final String memberName;

	private final String memberTel;

	private final String productName;

	private final Integer productPrice;

	private final LabelName Label;

	private final String loanName;

	private final String insuranceName;

	public static PurchaseHistoryDetailResponse from(final PurchaseHistory purchaseHistory) {
		Product p = purchaseHistory.getProduct();
		Member m = purchaseHistory.getMember();
		Loan l = purchaseHistory.getLoan();
		Insurance i = purchaseHistory.getInsurance();
		return new PurchaseHistoryDetailResponse(purchaseHistory.getId(), purchaseHistory.getCreateAt(),
			purchaseHistory.getDeliverySchedule(),
			m.getName(), m.getTel(),
			p.getName(), p.getPrice(), p.getLabel().getName(),
			l.getName(),
			i.getName());
	}

}
