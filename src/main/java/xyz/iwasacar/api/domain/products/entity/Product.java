package xyz.iwasacar.api.domain.products.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.iwasacar.api.domain.brands.entity.Brand;
import xyz.iwasacar.api.domain.colors.entity.Color;
import xyz.iwasacar.api.domain.common.constant.EntityStatus;
import xyz.iwasacar.api.domain.labels.entity.Label;
import xyz.iwasacar.api.domain.resources.entity.Resource;

@Table(name = "products")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_no")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_no", nullable = false)
	private Brand brand;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "label_no", nullable = false)
	private Label label;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "performance_check", nullable = false)
	private Resource performanceCheck;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "color_no", nullable = false)
	private Color color;

	@Column(name = "name", nullable = false, length = 20)
	private String name;

	@Column(name = "fake_product_status", nullable = false)
	private Boolean fakeProductStatus;

	@Column(name = "info", nullable = false)
	private String info;

	@Column(name = "transmission", nullable = false, length = 20)
	private String transmission;

	@Column(name = "fuel", nullable = false, length = 20)
	private String fuel;

	@Column(name = "driving_method", nullable = false, length = 20)
	private String drivingMethod;

	@Column(name = "\"year\"", nullable = false)
	private LocalDate year;

	@Column(name = "distance", nullable = false)
	private Integer distance;

	@Column(name = "price", nullable = false)
	private Integer price;

	@Column(name = "fuel_efficiency", nullable = false)
	private Double fuelEfficiency;

	@Column(name = "displacement", nullable = false)
	private Double displacement;

	@Column(name = "accident_history", nullable = false)
	private Integer accidentHistory;

	@Column(name = "inundation_history", nullable = false)
	private Boolean inundationHistory;

	@Column(name = "status", nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private EntityStatus status;

	@Column(name = "created_at", nullable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;

}
