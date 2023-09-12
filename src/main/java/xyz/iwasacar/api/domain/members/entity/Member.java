package xyz.iwasacar.api.domain.members.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.iwasacar.api.domain.common.constant.EntityStatus;
import xyz.iwasacar.api.domain.members.dto.request.UpdateRequest;

@Table(name = "members")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_no")
	private Long id;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "name", nullable = false, length = 20)
	private String name;

	@Column(name = "tel", nullable = false, length = 20)
	private String tel;

	@Column(name = "gender", nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "birth", nullable = false)
	private LocalDate birth;

	@Column(name = "has_license", nullable = false)
	private Boolean hasLicense;

	@Column(name = "status", nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private EntityStatus status;

	@Column(name = "created_at", nullable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = false)
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@Column(name = "last_login_at", nullable = false)
	private LocalDateTime lastLoginAt;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

	@Builder
	public Member(String email, String password, String name, String tel, Gender gender, LocalDate birth,
		Boolean hasLicense, EntityStatus status, LocalDateTime lastLoginAt) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.tel = tel;
		this.gender = gender;
		this.birth = birth;
		this.hasLicense = hasLicense;
		this.status = status;
		this.lastLoginAt = lastLoginAt;
	}

	public void updateLastLogin() {
		this.lastLoginAt = LocalDateTime.now();
	}

	public void update(final Gender gender, final String tel, final Boolean hasLicense) {
		this.gender = gender;
		this.tel = tel;
		this.hasLicense = hasLicense;
	}

	public void update(UpdateRequest request, String encodedPassword) {
		this.name = request.getName();
		this.password = encodedPassword;
		this.tel = request.getTel();
		this.gender = request.getGender();
		this.hasLicense = request.getHasLicense();
	}

	public void delete() {
		this.deletedAt = LocalDateTime.now();
		this.status = EntityStatus.DELETED;
	}

	public boolean isDeleted() {
		return this.status == EntityStatus.DELETED;
	}

}
