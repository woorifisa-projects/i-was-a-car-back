package xyz.iwasacar.api.domain.labels.entity;

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

@Entity
@Table(name = "labels")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Label {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "label_no", nullable = false)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "name", nullable = false, length = 20)
	private LabelName name;

	public Label(LabelName name) {
		this.name = name;
	}

}
