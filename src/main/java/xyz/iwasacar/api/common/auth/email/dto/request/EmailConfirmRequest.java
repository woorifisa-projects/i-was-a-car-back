package xyz.iwasacar.api.common.auth.email.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class EmailConfirmRequest {

	@NotBlank
	private String email;

	@NotBlank
	private String code;
}
