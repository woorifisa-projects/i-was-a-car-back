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

}
