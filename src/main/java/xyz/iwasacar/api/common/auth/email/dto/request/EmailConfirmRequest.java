package xyz.iwasacar.api.common.auth.email.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class EmailConfirmRequest {

	@NotBlank
	private String email;

	@NotBlank
	private String code;
}
