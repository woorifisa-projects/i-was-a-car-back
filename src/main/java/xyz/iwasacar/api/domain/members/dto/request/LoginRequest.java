package xyz.iwasacar.api.domain.members.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginRequest {

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String password;

}
