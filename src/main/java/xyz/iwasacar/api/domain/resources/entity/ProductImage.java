package xyz.iwasacar.api.domain.resources.entity;

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
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.iwasacar.api.domain.products.entity.Product;
import xyz.iwasacar.api.domain.roles.entity.Role;

@Table(name = "products_images")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProductImage {

	@EmbeddedId
	private Pk id;

	@MapsId(value = "productId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_no", nullable = false)
	private Product product;

	@MapsId(value = "resourceId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resource_no", nullable = false)
	private Resource resource;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_no", nullable = false)
	private Role role;

	@Builder
	public ProductImage(Product product, Resource resource, Role role) {
		this.id = new Pk(product.getId(), resource.getId());
		this.product = product;
		this.resource = resource;
		this.role = role;
	}

	@Embeddable
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@EqualsAndHashCode
	@Getter
	public static class Pk implements Serializable {

		private Long productId;
		private Long resourceId;

	}

}
