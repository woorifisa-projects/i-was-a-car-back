package xyz.iwasacar.api.domain.consenthistories.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ConsentHistoryRequest {
	private Long memberId;

	private Long productId;

	private Long documentItemId;

	private Boolean consent;

}
