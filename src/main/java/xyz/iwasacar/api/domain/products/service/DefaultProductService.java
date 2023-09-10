package xyz.iwasacar.api.domain.products.service;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.caroptions.entity.CarOption;
import xyz.iwasacar.api.domain.caroptions.repository.CarOptionRepository;
import xyz.iwasacar.api.domain.products.dto.response.AdminProductUpdateResponse;
import xyz.iwasacar.api.domain.products.dto.response.ProductDetailResponse;
import xyz.iwasacar.api.domain.products.dto.response.ProductResponse;
import xyz.iwasacar.api.domain.products.dto.response.ProductSaleDetailResponse;
import xyz.iwasacar.api.domain.products.entity.Product;
import xyz.iwasacar.api.domain.products.exception.ProductNotFound;
import xyz.iwasacar.api.domain.products.repository.ProductRepository;
import xyz.iwasacar.api.domain.resources.entity.Resource;
import xyz.iwasacar.api.domain.resources.repository.ResourceRepository;

@Primary
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
	public List<ProductResponse> findProducts(final Long category, final String keyword, final Long lastProductId) {

		return resourceRepository.findByProducts(category, keyword, lastProductId)
			.stream()
			.map(ProductResponse::of)
			.collect(toList());
	}

	@Override
	public PageResponse<ProductResponse> findWaitingProducts(Integer page, Integer size) {
		throw new IllegalArgumentException();
	}

	@Override
	public PageResponse<ProductResponse> findProducts(Integer page, Integer size) {
		throw new IllegalArgumentException();
	}

	@Override
	public List<ProductResponse> findSpecificProducts(
		final Long carType, final Integer capital, final Integer loan, final Long lastProductId
	) {

		return resourceRepository.findBySpecificProducts(carType, capital, loan, lastProductId)
			.stream()
			.map(ProductResponse::of)
			.collect(toList());
	}

	@Override
	public ProductDetailResponse updateProduct(
		Long productId, MultipartFile performanceCheck, List<MultipartFile> images
	) {
		throw new IllegalArgumentException();
	}

	@Override
	public ProductSaleDetailResponse findProductHistory(Long productId) {
		throw new IllegalArgumentException();
	}

	@Transactional
	@Override
	public void deleteProduct(final Long productId) {
		productRepository.getBy(productId).delete();
	}

	@Override
	public String addPerformanceCheck(Long productId, MultipartFile performanceCheck) {
		throw new IllegalArgumentException();
	}

	@Override
	public List<String> addAdminImages(Long productId, List<MultipartFile> images) {
		throw new IllegalArgumentException();
	}

	@Override
	public AdminProductUpdateResponse updatePriceAndLabel(Long productId, Integer price, Long labelId) {
		throw new IllegalArgumentException();
	}
}
