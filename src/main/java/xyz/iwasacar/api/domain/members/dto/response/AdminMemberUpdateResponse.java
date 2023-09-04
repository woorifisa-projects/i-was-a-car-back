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
public class AdminMemberUpdateResponse {

	private final Long id;
	private final String email;
	private final String name;
	private final Gender gender;
	private final String tel;
	private final Boolean hasLicense;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private final LocalDateTime lastLogin;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private final LocalDateTime updatedAt;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private final LocalDateTime createdAt;

	public static AdminMemberUpdateResponse from(Member member) {
		return AdminMemberUpdateResponse.builder()
			.id(member.getId())
			.email(member.getEmail())
			.name(member.getName())
			.gender(member.getGender())
			.tel(member.getTel())
			.hasLicense(member.getHasLicense())
			.lastLogin(member.getLastLoginAt())
			.createdAt(member.getCreatedAt())
			.updatedAt(member.getUpdatedAt())
			.build();
	}

}
