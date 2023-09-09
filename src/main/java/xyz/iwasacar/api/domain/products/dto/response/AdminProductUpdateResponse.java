package xyz.iwasacar.api.domain.products.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.labels.entity.LabelName;

@RequiredArgsConstructor
@Getter
public class AdminProductUpdateResponse {

	private final Integer price;
	private final Long labelId;
	private final LabelName labelName;

}
