package xyz.iwasacar.api.domain.documentitems.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.documentitems.entity.DocumentItem;

@Getter
@RequiredArgsConstructor
public class DocumentItemResponse {
	private final Long documentId;

	private final String documentName;
	private final Integer order;
	private final String content;

	public static DocumentItemResponse of(DocumentItem documentItem) {
		return new DocumentItemResponse(documentItem.getDocument().getId(), documentItem.getDocument().getName(),
			documentItem.getOrder(),
			documentItem.getContent());
	}
}
