package xyz.iwasacar.api.domain.members.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.iwasacar.api.domain.members.entity.Gender;

@NoArgsConstructor
@Getter
public class UpdateRequest {

	@NotBlank
	private String name;

	@NotBlank
	private String password;

	@NotBlank
	private String tel;

	@NotNull
	private Gender gender;

	@NotNull
	private Boolean hasLicense;

}
