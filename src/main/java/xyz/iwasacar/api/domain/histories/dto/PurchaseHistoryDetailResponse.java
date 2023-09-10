package xyz.iwasacar.api.domain.histories.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.histories.entity.PurchaseHistory;
import xyz.iwasacar.api.domain.insurances.entity.Insurance;
import xyz.iwasacar.api.domain.loans.entity.Loan;
import xyz.iwasacar.api.domain.members.entity.Member;
import xyz.iwasacar.api.domain.products.entity.Product;

@RequiredArgsConstructor
@Getter
public class PurchaseHistoryDetailResponse {
	//반환해야할 값
	// 고객정보 (수령인, 연락처, 우편 번호, 탁송 주소, 탁송 예정일) , 상품정보 (차량명, 계약 일자, 차량 가격, 은행, 예금주, 계좌 번, 대출 정보, 보험 정보)

	private final String memberName;

	private final String memberTel;

	private final String zipCode;

	private final String address;

	private final LocalDateTime deliverySchedule;

	private final String productName;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private final LocalDateTime createdAt;

	private final Integer productPrice;

	private final String bankName;

	private final String accountHolder;

	private final String accountNumber;

	private final String loanName;

	private final String insuranceName;

	public static PurchaseHistoryDetailResponse from(final PurchaseHistory purchaseHistory) {
		Product p = purchaseHistory.getProduct();
		Member m = purchaseHistory.getMember();
		Loan l = purchaseHistory.getLoan();
		Insurance i = purchaseHistory.getInsurance();
		return new PurchaseHistoryDetailResponse(m.getName(), m.getTel(),
			purchaseHistory.getZipCode(), purchaseHistory.getAddress(), purchaseHistory.getDeliverySchedule(),
			p.getName(), purchaseHistory.getCreateAt(), p.getPrice(), purchaseHistory.getBank().getName(),
			purchaseHistory.getAccountHolder(), purchaseHistory.getAccountNumber(), purchaseHistory.getLoan().getName(),
			purchaseHistory.getInsurance().getName());
	}

}
