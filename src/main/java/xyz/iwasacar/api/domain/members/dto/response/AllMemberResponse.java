package xyz.iwasacar.api.domain.members.dto.response;

import java.time.LocalDate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.members.entity.Gender;

@Getter
@RequiredArgsConstructor
public class AllMemberResponse {

	private final String email;
	private final String name;
	private final String tel;
	private final LocalDate birth;
	private final Gender gender;
	private final Boolean hasLicense;
}
