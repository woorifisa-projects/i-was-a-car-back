package xyz.iwasacar.api.domain.members.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.members.entity.Gender;
import xyz.iwasacar.api.domain.members.entity.Member;

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
		return new MemberUpdateResponse(
			m.getName(),
			m.getPassword(),
			m.getTel(),
			m.getGender(),
			m.getHasLicense(),
			m.getLastLoginAt()
		);
	}
}
