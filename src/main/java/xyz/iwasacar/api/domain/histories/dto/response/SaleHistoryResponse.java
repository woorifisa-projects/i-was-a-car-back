package xyz.iwasacar.api.domain.histories.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.labels.entity.LabelName;

@RequiredArgsConstructor
@Getter
public class SaleHistoryResponse {
	private final Long saleHistoryNo;

	private final String productName;

	private final LabelName Label;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private final LocalDateTime createdAt;

}
