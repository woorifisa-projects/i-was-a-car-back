package xyz.iwasacar.api.domain.consenthistories.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.consenthistories.entity.DocumentConsentHistory;

@Getter
@RequiredArgsConstructor
public class ConsentHistoryResponse {

	private final Long memberId;

	private final Long productId;

	private final Long documentItemId;

	private final Boolean consent;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private final LocalDateTime createdAt;

	public static ConsentHistoryResponse of(DocumentConsentHistory documentConsentHistory) {
		Long productId =
			documentConsentHistory.getProduct() == null ? null : documentConsentHistory.getProduct().getId();
		return new ConsentHistoryResponse(documentConsentHistory.getMember().getId(),
			productId, documentConsentHistory.getDocumentItem().getId(),
			documentConsentHistory.getConsent(), documentConsentHistory.getCreatedAt());
	}

}
