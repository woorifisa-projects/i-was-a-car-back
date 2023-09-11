package xyz.iwasacar.api.domain.histories.dto.request;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class PurchaseHistoryRequest {

	@NotNull
	private Long memberId;
	@NotNull
	private Long productId;
	@NotNull
	private Long bankId;
	@NotNull
	private Long loanId;
	@NotNull
	private Long insuranceId;
	@NotNull
	private String zipCode;
	@NotNull
	private String address;
	@NotNull
	private String addressDetail;
	@NotNull
	private String accountNumber;
	@NotNull
	private String accountHolder;
	@NotNull
	private Integer loanAmount;
	@NotNull
	private Integer period;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime deliverySchedule;

	// 을 붙인 경우 생성자를 쓰려면 생성자 만들고 @JsonCreator가 있어야 Joson 형태로 만들어준다.
	// 원래는  안 붙이고 기본 생성자 어노테이션 class에 붙임
	// @JsonCreator
	// public PurchaseHistoryRequest(Long memberId, Long productId, Long bankId, Long loanId, Long insuranceId,
	// 	String zipcode, String address, String addressDetail, String accountNumber, String accountHolder,
	// 	Integer loanAmount, Integer period,
	// 	@JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime deliverySchedule) {
	// 	this.memberId = memberId;
	// 	this.productId = productId;
	// 	this.bankId = bankId;
	// 	this.loanId = loanId;
	// 	this.insuranceId = insuranceId;
	// 	this.zipCode = zipcode;
	// 	this.address = address;
	// 	this.addressDetail = addressDetail;
	// 	this.accountNumber = accountNumber;
	// 	this.accountHolder = accountHolder;
	// 	this.loanAmount = loanAmount;
	// 	this.period = period;
	// 	this.deliverySchedule = deliverySchedule;
	// }

}
