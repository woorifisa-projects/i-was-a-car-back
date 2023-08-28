package xyz.iwasacar.api.domain.members.dto.response;

import lombok.Getter;
import xyz.iwasacar.api.common.auth.jwt.JwtDto;
import xyz.iwasacar.api.domain.members.entity.Member;

@Getter
public class MemberJwtResponse {

	private final MemberResponse memberResponse;
	private final JwtDto jwtDto;

	public MemberJwtResponse(Member member, JwtDto jwtDto) {
		this.memberResponse = memberResponse(member);
		this.jwtDto = jwtDto;
	}

	private MemberResponse memberResponse(Member member) {
		return MemberResponse.builder()
			.id(member.getId())
			.email(member.getEmail())
			.name(member.getName())
			.tel(member.getTel())
			.gender(member.getGender())
			.hasLicense(member.getHasLicense())
			.build();
	}

	public MemberResponse getMemberResponse() {
		return this.memberResponse;
	}

}
