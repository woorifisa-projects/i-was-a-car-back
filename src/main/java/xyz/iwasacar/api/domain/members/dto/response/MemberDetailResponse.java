package xyz.iwasacar.api.domain.members.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.members.entity.Gender;
import xyz.iwasacar.api.domain.members.entity.Member;

@Getter
@RequiredArgsConstructor
public class MemberDetailResponse {

	private final String email;

	private final String name;

	private final String tel;

	private final Gender gender;

	private final Boolean hasLicense;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private final LocalDateTime lastLoginAt;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private final LocalDateTime createdAt;



	public static MemberDetailResponse from(Member m) {

		return new MemberDetailResponse(m.getEmail(),
			m.getName(),
			m.getTel(),
			m.getGender(),
			m.getHasLicense(),
			m.getLastLoginAt(),
			m.getCreatedAt());
	}

}
