package xyz.iwasacar.api.domain.products.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.labels.entity.LabelName;
import xyz.iwasacar.api.domain.products.entity.Product;

@RequiredArgsConstructor
@Getter
public class ProductsResponse {
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

	public static List<ProductsResponse> of(List<Product> products, List<String> resources) {
		List<ProductsResponse> result = new ArrayList<>();
		for (int i = 0; i < products.size(); i++) {
			Product p = products.get(i);
			String s = resources.get(i);
			result.add(new ProductsResponse(p.getId(), p.getName(), p.getBrand().getName(), p.getLabel().getName(),
				p.getDrivingMethod(), p.getDisplacement(), p.getPrice(), s, p.getCreatedAt()));
		}
		return result;
	}
}
