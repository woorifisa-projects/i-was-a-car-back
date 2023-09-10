package xyz.iwasacar.api.domain.documentitems.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.documentitems.entity.DocumentItem;

@Builder
@RequiredArgsConstructor
@Getter
public class DocumentItemResponse {

	private final Long documentId;
	private final String documentName;

	private final Long documentItemId;
	private final Integer order;
	private final String content;

	public static DocumentItemResponse of(DocumentItem documentItem) {

		return DocumentItemResponse.builder()
			.documentId(documentItem.getDocument().getId())
			.documentName(documentItem.getDocument().getName())
			.documentItemId(documentItem.getId())
			.order(documentItem.getOrder())
			.content(documentItem.getContent())
			.build();
	}
}
