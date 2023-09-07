package xyz.iwasacar.api.domain.members.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private final LocalDateTime createdAt;
}
