package xyz.iwasacar.api.domain.members.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import xyz.iwasacar.api.common.auth.jwt.Jwt;
import xyz.iwasacar.api.domain.common.constant.EntityStatus;
import xyz.iwasacar.api.domain.members.entity.Gender;
import xyz.iwasacar.api.domain.members.entity.Member;

@Getter
public class MemberResponse {

	private Long id;

	private String email;

	private String password;

	private String name;

	private String tel;

	private Gender gender;

	private LocalDate birth;

	private Boolean hasLicense;

	private EntityStatus status;

	private LocalDateTime lastLoginAt;

	@JsonIgnore
	private Jwt jwt;

	public MemberResponse(Member member, Jwt jwt) {
		this.id = member.getId();
		this.email = member.getEmail();
		this.password = member.getPassword();
		this.name = member.getName();
		this.tel = member.getTel();
		this.gender = member.getGender();
		this.birth = member.getBirth();
		this.hasLicense = member.getHasLicense();
		this.status = member.getStatus();
		this.lastLoginAt = member.getLastLoginAt();
		this.jwt = jwt;
	}

}
