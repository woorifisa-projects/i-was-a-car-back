package xyz.iwasacar.api.domain.banks.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "banks")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Bank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bank_no")
	private Long id;

	@Column(name = "name", nullable = false, length = 20)
	private String name;

	@Builder
	public Bank(String name) {
		this.name = name;
	}
}
