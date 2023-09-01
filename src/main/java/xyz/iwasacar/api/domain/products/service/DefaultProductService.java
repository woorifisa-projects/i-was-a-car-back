package xyz.iwasacar.api.domain.products.service;

import static java.util.stream.Collectors.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.caroptions.entity.CarOption;
import xyz.iwasacar.api.domain.caroptions.repository.CarOptionRepository;
import xyz.iwasacar.api.domain.products.dto.response.ProductDetailResponse;
import xyz.iwasacar.api.domain.products.dto.response.ProductResponse;
import xyz.iwasacar.api.domain.products.entity.Product;
import xyz.iwasacar.api.domain.products.exception.ProductNotFound;
import xyz.iwasacar.api.domain.products.repository.ProductRepository;
import xyz.iwasacar.api.domain.resources.entity.Resource;
import xyz.iwasacar.api.domain.resources.repository.ResourceRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {

	private final ProductRepository productRepository;
	private final CarOptionRepository carOptionRepository;
	private final ResourceRepository resourceRepository;

	@Override
	public ProductDetailResponse findProductDetail(final Long id) {

		Product productDetail = productRepository.findProductDetail(id)
			.orElseThrow(ProductNotFound::new);

		Map<String, List<String>> carOptionGroup =
			CarOption.convertCarOption(carOptionRepository.findOptionsByProductId(id));

		List<String> resources = resourceRepository.findByProductId(id)
			.stream()
			.map(Resource::getUrl)
			.collect(toList());

		return ProductDetailResponse.of(productDetail, resources, carOptionGroup);
	}

	@Override
	public List<ProductResponse> findProducts(final Long lastProductId) {

		return resourceRepository.findByProducts(lastProductId)
			.stream()
			.map(ProductResponse::of)
			.collect(toList());
	}

	@Override
	public List<ProductResponse> findSpecificProducts(Long carType, Integer capital, Integer loan, Long lastProductId) {
		return resourceRepository.findBySepcificProducts(carType, capital, loan, lastProductId)
			.stream()
			.map(ProductResponse::of)
			.collect(toList());
	}

	private Map<String, List<String>> convertCarOption(Map<String, List<CarOption>> options) {
		Map<String, List<String>> map = new HashMap<>();
		options.forEach(
			(key, value) -> map.put(key, value
				.stream()
				.map(CarOption::getName)
				.collect(toList()))
		);
		return map;
	}

}
