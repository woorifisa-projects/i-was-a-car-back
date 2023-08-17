package xyz.iwasacar.api.domain.members.dto.request;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.iwasacar.api.domain.members.entity.Gender;

@NoArgsConstructor
@Getter
public class SignupRequest {

	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String password;

	@NotBlank
	private String name;

	@NotNull
	private LocalDate birth;

	@NotBlank
	private String tel;

	@NotNull
	private Gender gender;

	@NotNull
	private Boolean hasLicense;

}
