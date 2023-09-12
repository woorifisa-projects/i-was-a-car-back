package xyz.iwasacar.api.domain.members.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.auth.jwt.JwtDto;
import xyz.iwasacar.api.domain.members.entity.Member;
import xyz.iwasacar.api.domain.roles.entity.RoleName;

@RequiredArgsConstructor
@Getter
public class MemberJwtResponse {

	private final MemberResponse memberResponse;
	private final JwtDto jwtDto;

	public MemberJwtResponse(Member member, JwtDto jwtDto, List<RoleName> roles) {
		this.memberResponse = memberResponse(member, roles);
		this.jwtDto = jwtDto;
	}

	private MemberResponse memberResponse(Member member, List<RoleName> roles) {
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
