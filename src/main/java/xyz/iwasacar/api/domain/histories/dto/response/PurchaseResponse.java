package xyz.iwasacar.api.domain.histories.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.histories.entity.PurchaseHistory;
import xyz.iwasacar.api.domain.labels.entity.LabelName;
import xyz.iwasacar.api.domain.products.entity.Product;

@RequiredArgsConstructor
@Getter
public class PurchaseResponse {

	private final Long purchaseHistoryNo;

	private final String productName;

	private final LabelName Label;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private final LocalDateTime createdAt;

	public static PurchaseResponse from(final PurchaseHistory purchaseHistory) {
		Product p = purchaseHistory.getProduct();
		return new PurchaseResponse(purchaseHistory.getId(),
			p.getName(),
			p.getLabel().getName(),
			purchaseHistory.getCreateAt());
	}
}
