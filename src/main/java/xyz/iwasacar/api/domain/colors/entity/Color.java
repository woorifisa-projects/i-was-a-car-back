package xyz.iwasacar.api.domain.colors.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "colors")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Color {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "color_no")
	private Long id;

	@Column(name = "name", nullable = false, length = 20)
	private String name;

	public Color(String name) {
		this.name = name;
	}
}
