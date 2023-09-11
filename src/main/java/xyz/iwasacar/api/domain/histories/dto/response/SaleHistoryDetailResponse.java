package xyz.iwasacar.api.domain.histories.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.labels.entity.LabelName;

@Builder
@RequiredArgsConstructor
@Getter
public class SaleHistoryDetailResponse {

	private final String sellerName;

	private final String sellerTel;

	private final String zipCode;

	private final String address;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private final LocalDateTime meetingSchedule;

	private final String productName;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private final LocalDateTime createdAt;

	private final Integer productPrice;

	private final String bankName;

	private final String accountHolder;

	private final String accountNum;

	private final LabelName labelName;

}
