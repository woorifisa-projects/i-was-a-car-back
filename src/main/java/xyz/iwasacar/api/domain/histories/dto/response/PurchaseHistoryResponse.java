package xyz.iwasacar.api.domain.histories.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.histories.entity.PurchaseHistory;

@Builder
@Getter
@RequiredArgsConstructor
public class PurchaseHistoryResponse {

	private final Long id;
	private final String memberName;
	private final Long productId;
	private final String productName;
	private final String bankName;
	private final String loanName;
	private final String insuranceName;
	private final String zipCode;
	private final String address;
	private final String addressDetail;
	private final String accountNumber;
	private final String accountHolder;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private final LocalDateTime deliverySchedule;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private final LocalDateTime createAt;

	public static PurchaseHistoryResponse of(PurchaseHistory purchaseHistory) {
		return new PurchaseHistoryResponse(purchaseHistory.getId(), purchaseHistory.getMember().getName(),
			purchaseHistory.getProduct().getId(), purchaseHistory.getProduct().getName(),
			purchaseHistory.getBank().getName(), purchaseHistory.getLoan().getName(),
			purchaseHistory.getInsurance().getName(), purchaseHistory.getZipCode(), purchaseHistory.getAddress(),
			purchaseHistory.getAddressDetail(), purchaseHistory.getAccountNumber(), purchaseHistory.getAccountHolder(),
			purchaseHistory.getDeliverySchedule(), purchaseHistory.getCreateAt());
	}

}
