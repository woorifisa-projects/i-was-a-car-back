package xyz.iwasacar.api.domain.caroptions.entity;

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
import xyz.iwasacar.api.domain.products.entity.Product;

@Table(name = "products_options")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProductOption {

	@EmbeddedId
	private Pk id;

	@MapsId(value = "productId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_no", nullable = false)
	private Product product;

	@MapsId(value = "carOptionId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "car_option_no", nullable = false)
	private CarOption carOption;

	public ProductOption(Product product, CarOption carOption) {
		this.id = new Pk(product.getId(), carOption.getId());
		this.product = product;
		this.carOption = carOption;
	}

	@Embeddable
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor
	@EqualsAndHashCode
	@Getter
	public static class Pk implements Serializable {
		private Long productId;
		private Long carOptionId;
	}

}
