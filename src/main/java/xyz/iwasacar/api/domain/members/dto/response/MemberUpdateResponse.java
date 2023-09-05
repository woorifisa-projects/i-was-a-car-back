package xyz.iwasacar.api.domain.members.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.members.entity.Gender;
import xyz.iwasacar.api.domain.members.entity.Member;

@Builder
@Getter
@RequiredArgsConstructor
public class MemberUpdateResponse {

	private final String name;

	private final String password;

	private final String tel;

	private final Gender gender;

	private final Boolean hasLicense;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private final LocalDateTime lastLoginAt;

	public static MemberUpdateResponse from(Member m) {
		return MemberUpdateResponse
			.builder()
			.name(m.getName())
			.password(m.getPassword())
			.tel(m.getTel())
			.gender(m.getGender())
			.hasLicense(m.getHasLicense())
			.lastLoginAt(m.getLastLoginAt())
			.build();
	}
}
