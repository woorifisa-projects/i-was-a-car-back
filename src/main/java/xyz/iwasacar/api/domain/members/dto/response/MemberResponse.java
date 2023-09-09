package xyz.iwasacar.api.domain.members.dto.response;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.members.entity.Gender;
import xyz.iwasacar.api.domain.members.entity.Member;
import xyz.iwasacar.api.domain.roles.entity.RoleName;

@Builder
@RequiredArgsConstructor
@Getter
public class MemberResponse implements Serializable {

	private static final long serialVersionUID = 2920753163261664224L;

	private final Long id;
	private final String email;
	private final String name;
	private final String tel;
	private final Gender gender;
	private final Boolean hasLicense;
	private final List<RoleName> roles;

	@JsonFormat(pattern = "yyMMdd")
	private final LocalDate birth;

	public static MemberResponse from(Member member, List<RoleName> roles) {
		return MemberResponse.builder()
			.id(member.getId())
			.email(member.getEmail())
			.name(member.getName())
			.tel(member.getTel())
			.gender(member.getGender())
			.hasLicense(member.getHasLicense())
			.roles(roles)
			.birth(member.getBirth())
			.build();
	}

}
