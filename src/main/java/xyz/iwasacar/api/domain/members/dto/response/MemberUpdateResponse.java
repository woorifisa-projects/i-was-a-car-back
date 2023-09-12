package xyz.iwasacar.api.domain.members.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.members.entity.Gender;
import xyz.iwasacar.api.domain.members.entity.Member;

@Builder
@RequiredArgsConstructor
@Getter
public class MemberUpdateResponse {

	private final String name;
	private final String password;
	private final String tel;
	private final Gender gender;
	private final Boolean hasLicense;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private final LocalDateTime lastLoginAt;

	public static MemberUpdateResponse from(Member member) {
		return MemberUpdateResponse
			.builder()
			.name(member.getName())
			.password(member.getPassword())
			.tel(member.getTel())
			.gender(member.getGender())
			.hasLicense(member.getHasLicense())
			.lastLoginAt(member.getLastLoginAt())
			.build();
	}
}
