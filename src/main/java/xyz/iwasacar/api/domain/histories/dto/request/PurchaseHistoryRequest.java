package xyz.iwasacar.api.domain.histories.dto.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class PurchaseHistoryRequest {

	private Long memberId;
	private Long productId;
	private Long bankId;
	private Long loanId;
	private Long insuranceId;
	private String zipCode;
	private String address;
	private String addressDetail;
	private String accountNumber;
	private String accountHolder;
	private Integer loanAmount;
	private Integer period;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime deliverySchedule;

}
