package xyz.iwasacar.api.domain.products.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.labels.entity.LabelName;
import xyz.iwasacar.api.domain.products.entity.Product;
import xyz.iwasacar.api.domain.resources.entity.ProductImage;
import xyz.iwasacar.api.domain.resources.entity.Resource;

@RequiredArgsConstructor
@Getter
public class ProductResponse {
	private final Long id;
	private final String name;
	private final String brand;
	private final LabelName label;
	private final String drivingMethod;
	private final Double displacement;
	private final Integer price;
	private final String images;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private final LocalDateTime createdAt;

	public static ProductResponse of(ProductImage productImage) {
		Product p = productImage.getProduct();
		Resource image = productImage.getResource();

		return new ProductResponse(p.getId(), p.getName(), p.getBrand().getName(), p.getLabel().getName(),
			p.getDrivingMethod(), p.getDisplacement(), p.getPrice(), image.getUrl(), p.getCreatedAt());
	}

}
