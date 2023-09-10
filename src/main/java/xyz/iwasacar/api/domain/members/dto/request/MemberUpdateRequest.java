package xyz.iwasacar.api.domain.members.dto.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.iwasacar.api.domain.members.entity.Gender;

@NoArgsConstructor
@Getter
public class MemberUpdateRequest {

	@NotNull
	private Gender gender;

	@NotNull
	private String tel;

	@NotNull
	private Boolean hasLicense;

}
