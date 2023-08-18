package xyz.iwasacar.api.domain.members.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.iwasacar.api.common.auth.jwt.Jwt;
import xyz.iwasacar.api.domain.common.constant.EntityStatus;
import xyz.iwasacar.api.domain.members.entity.Gender;
import xyz.iwasacar.api.domain.members.entity.Member;

@NoArgsConstructor
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

	@Enumerated(EnumType.STRING)
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
