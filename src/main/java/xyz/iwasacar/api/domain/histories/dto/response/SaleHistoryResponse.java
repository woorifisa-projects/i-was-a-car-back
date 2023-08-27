package xyz.iwasacar.api.domain.histories.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.labels.entity.LabelName;

@RequiredArgsConstructor
@Getter
public class SaleHistoryResponse {

	private final Long id;

	private final LocalDateTime createdAt; // 등록날짜

	private final String productName; //  상품명

	private final LabelName label; // 라벨명

}
