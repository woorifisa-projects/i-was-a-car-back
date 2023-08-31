package xyz.iwasacar.api.domain.histories.dto.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PurchaseHistoryRequest {

	private final Long memberId;

	private final Long productId;

	private final Long bankId;

	private final Long loanId;

	private final Long insuranceId;

	private final String zipCode;

	private final String address;

	private final String addressDetail;

	private final String accountNumber;

	private final String accountHolder;

	private final LocalDateTime deliverySchedule;

	// final을 붙인 경우 생성자를 쓰려면 생성자 만들고 @JsonCreator가 있어야 Joson 형태로 만들어준다.
	// 원래는 final 안 붙이고 기본 생성자 어노테이션 class에 붙임
	@JsonCreator
	public PurchaseHistoryRequest(Long memberId, Long productId, Long bankId, Long loanId, Long insuranceId,
		String zipcode, String address, String addressDetail, String accountNumber, String accountHolder,
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime deliverySchedule) {
		this.memberId = memberId;
		this.productId = productId;
		this.bankId = bankId;
		this.loanId = loanId;
		this.insuranceId = insuranceId;
		this.zipCode = zipcode;
		this.address = address;
		this.addressDetail = addressDetail;
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
		this.deliverySchedule = deliverySchedule;
	}
}
