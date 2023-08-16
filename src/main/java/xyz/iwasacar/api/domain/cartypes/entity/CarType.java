package xyz.iwasacar.api.domain.cartypes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "car_types")
public class CarType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_type_no")
	private Long id;

	@Column(name = "name", nullable = false, length = 20)
	private String name;
}
