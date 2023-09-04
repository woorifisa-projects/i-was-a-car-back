package xyz.iwasacar.api.domain.products.service;

import static java.util.stream.Collectors.*;

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

@Service("AdminProductService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminProductService implements ProductService {

	private final ProductRepository productRepository;
	private final ResourceRepository resourceRepository;
	private final CarOptionRepository carOptionRepository;

	@Override
	public ProductDetailResponse findProductDetail(final Long id) {

		Product productDetail = productRepository.findProductDetailAdmin(id)
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
	public List<ProductResponse> findProducts(Long id) {
		throw new IllegalArgumentException();
	}

	@Override
	public List<ProductResponse> findProducts(final int page, final int size) {
		// TODO: 쿼리 수정
		return resourceRepository.findByProducts(page, size)
			.stream()
			.map(ProductResponse::of)
			.collect(toList());
	}

	@Override
	public List<ProductResponse> findSpecificProducts(Long carType, Integer capital, Integer loan, Long lastProductId) {
		throw new IllegalArgumentException();
	}
}
