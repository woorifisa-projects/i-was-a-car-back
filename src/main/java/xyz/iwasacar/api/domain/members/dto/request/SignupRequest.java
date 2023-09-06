package xyz.iwasacar.api.domain.members.dto.request;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.iwasacar.api.domain.members.entity.Gender;

@NoArgsConstructor
@Getter
public class SignupRequest {

	@NotBlank(message = "이메일은 필수 입력 값입니다.")
	@Email(message = "이메일 형식이 올바르지 않습니다.")
	private String email;

	@NotBlank(message = "이메일 인증번호는 필수 입력 값입니다.")
	private String code;

	@NotBlank(message = "비밀번호는 필수 입력 값입니다.")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
		message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
	private String password;

	@NotBlank(message = "이름은 필수 입력 값입니다.")
	private String name;

	@NotNull(message = "생년월일은 필수 입력 값입니다.")
	private LocalDate birth;

	@NotBlank(message = "전화번호는 필수 입력 값입니다.")
	private String tel;

	@NotNull(message = "성별은 필수 입력 값입니다.")
	private Gender gender;

	@NotNull(message = "면허 유무는 필수 입력 값입니다.")
	private Boolean hasLicense;

}

