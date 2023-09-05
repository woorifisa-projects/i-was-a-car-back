package xyz.iwasacar.api.common.auth.email.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class EmailRequest {
	@NotBlank
	private String email;
}

