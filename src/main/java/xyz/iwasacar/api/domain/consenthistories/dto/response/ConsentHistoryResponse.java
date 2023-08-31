package xyz.iwasacar.api.domain.consenthistories.dto.response;

import java.time.LocalDateTime;

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

	private final LocalDateTime createdAt;

	public static ConsentHistoryResponse of(DocumentConsentHistory documentConsentHistory) {
		Long productId =
			documentConsentHistory.getProduct() == null ? null : documentConsentHistory.getProduct().getId();
		return new ConsentHistoryResponse(documentConsentHistory.getMember().getId(),
			productId, documentConsentHistory.getDocumentItem().getId(),
			documentConsentHistory.getConsent(), documentConsentHistory.getCreatedAt());
	}

}
