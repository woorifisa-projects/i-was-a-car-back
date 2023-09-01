package xyz.iwasacar.api.domain.histories.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.labels.entity.Label;
import xyz.iwasacar.api.domain.labels.entity.LabelName;

@RequiredArgsConstructor
@Getter
public class SaleHistoryDetailResponse {
	// 입력값 : 판매인, 연락처, 우편 번호, 미팅 주소, 미팅 예정일, 차량명, 계약 일자, 차량 가격
	// 은행, 예금주, 계좌 번호, 차량 심사상태

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

	private final String accountNum;

	private final LabelName labelName;

}
