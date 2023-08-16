package xyz.iwasacar.api.domain.roles.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.iwasacar.api.domain.members.entity.Member;

@Table(name = "members_roles")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberRole {

	@EmbeddedId
	private Pk id;

	@MapsId(value = "roleId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_no")
	private Role role;

	@MapsId(value = "memberId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_no")
	private Member member;

	public MemberRole(Role role, Member member) {
		this.id = new Pk(role.getId(), member.getId());
		this.role = role;
		this.member = member;
	}

	@Embeddable
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor
	@EqualsAndHashCode
	@Getter
	public static class Pk implements Serializable {

		private Long roleId;
		private Long memberId;

	}

}
