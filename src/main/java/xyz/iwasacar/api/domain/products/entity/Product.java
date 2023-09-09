package xyz.iwasacar.api.domain.products.entity;

import static xyz.iwasacar.api.domain.common.constant.EntityStatus.*;

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
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.iwasacar.api.domain.brands.entity.Brand;
import xyz.iwasacar.api.domain.cartypes.entity.CarType;
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
	@JoinColumn(name = "car_type_no", nullable = false)
	private CarType carType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_no", nullable = false)
	private Brand brand;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "label_no", nullable = false)
	private Label label;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "performance_check")
	private Resource performanceCheck;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "color_no", nullable = false)
	private Color color;

	@Column(name = "name", nullable = false, length = 20)
	private String name;

	@Column(name = "fake_product_status", nullable = false)
	private Boolean fakeProductStatus;

	@Column(name = "info", nullable = false)
	private String info;    // 번호판

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

	@Column(name = "updated_at", nullable = false)
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

	@Builder
	public Product(CarType carType, Brand brand, Label label, Color color, String name,
		Boolean fakeProductStatus, String info, String transmission, String fuel, String drivingMethod, LocalDate year,
		Integer distance, Integer price, Double fuelEfficiency, Double displacement, Integer accidentHistory,
		Boolean inundationHistory) {
		this.carType = carType;
		this.brand = brand;
		this.label = label;
		this.color = color;
		this.name = name;
		this.fakeProductStatus = fakeProductStatus;
		this.info = info;
		this.transmission = transmission;
		this.fuel = fuel;
		this.drivingMethod = drivingMethod;
		this.year = year;
		this.distance = distance;
		this.price = price;
		this.fuelEfficiency = fuelEfficiency;
		this.displacement = displacement;
		this.accidentHistory = accidentHistory;
		this.inundationHistory = inundationHistory;
		this.status = CREATED;
	}

	public void addPerformanceCheck(final Resource performanceCheck) {
		this.performanceCheck = performanceCheck;
	}

	public static Product changeLabel(final Product product, final Label newLabel) {
		product.label = newLabel;
		return product;
	}

	public void delete() {
		this.deletedAt = LocalDateTime.now();
		this.status = DELETED;
	}

	public void updateLabel(final Label label) {
		this.label = label;
	}

	public void updatePrice(final Integer price) {
		this.price = price;
	}

}
