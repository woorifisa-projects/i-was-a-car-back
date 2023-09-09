package xyz.iwasacar.api.domain.products.dto.reqeust;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AdminProductUpdateRequest {

	@NotNull
	private Integer price;

	@NotNull
	private Long labelId;

}
