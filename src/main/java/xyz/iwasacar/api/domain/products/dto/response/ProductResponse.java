package xyz.iwasacar.api.domain.products.dto.response;

import java.time.LocalDate;
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
	private final String carType;
	private final String name;
	private final String brand;
	private final String info;
	private final LabelName label;
	private final String fuel;
	private final String drivingMethod;
	private final Double displacement;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private final LocalDate year;
	private final Integer distance;
	private final Integer price;
	private final String images;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private final LocalDateTime createdAt;

	public static ProductResponse of(ProductImage productImage) {
		Product p = productImage.getProduct();
		Resource image = productImage.getResource();

		return new ProductResponse(p.getId(), p.getCarType().getName(), p.getName(), p.getBrand().getName(),
			p.getInfo(), p.getLabel().getName(), p.getFuel(), p.getDrivingMethod(), p.getDisplacement(), p.getYear(),
			p.getDistance(), p.getPrice(), image.getUrl(), p.getCreatedAt());
	}

}
