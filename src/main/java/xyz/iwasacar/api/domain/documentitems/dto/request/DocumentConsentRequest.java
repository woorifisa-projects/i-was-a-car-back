package xyz.iwasacar.api.domain.documentitems.dto.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DocumentConsentRequest {

	@NotNull
	private Long documentId;

	private Long productId;

	@NotNull
	private List<Long> documentItemId;

}
