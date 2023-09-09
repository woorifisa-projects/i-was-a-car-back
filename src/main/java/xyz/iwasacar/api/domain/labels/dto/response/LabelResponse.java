package xyz.iwasacar.api.domain.labels.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.labels.entity.Label;
import xyz.iwasacar.api.domain.labels.entity.LabelName;

@RequiredArgsConstructor
@Getter
public class LabelResponse {

	private final Long id;
	private final LabelName name;

	public static LabelResponse from(Label label) {
		return new LabelResponse(label.getId(), label.getName());
	}

}
