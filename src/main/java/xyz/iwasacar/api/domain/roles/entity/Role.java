package xyz.iwasacar.api.domain.roles.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "roles")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_no")
	private Long id;

	@Enumerated(EnumType.STRING)
	private RoleName name;

}
