package xyz.iwasacar.api.domain.members.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import xyz.iwasacar.api.common.auth.jwt.Jwt;
import xyz.iwasacar.api.domain.members.entity.Gender;
import xyz.iwasacar.api.domain.members.entity.Member;

@Getter
public class MemberResponse {

	private Long id;

	private String email;

	private String name;

	private String tel;

	private Gender gender;

	private Boolean hasLicense;

	@JsonIgnore
	private Jwt jwt;

	public MemberResponse(Member member, Jwt jwt) {
		this.id = member.getId();
		this.email = member.getEmail();
		this.name = member.getName();
		this.tel = member.getTel();
		this.gender = member.getGender();
		this.hasLicense = member.getHasLicense();
		this.jwt = jwt;
	}

}
