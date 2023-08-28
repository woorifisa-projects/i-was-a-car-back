package xyz.iwasacar.api.domain.brands.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "brand")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Brand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "brand_no")
	private Long id;

	@Column(name = "name", nullable = false, length = 20)
	private String name;

	public Brand(String name) {
		this.name = name;
	}
}
