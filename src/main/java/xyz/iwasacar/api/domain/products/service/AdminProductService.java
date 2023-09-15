package xyz.iwasacar.api.domain.products.service;

import static java.util.stream.Collectors.*;
import static xyz.iwasacar.api.common.component.AwsS3Uploader.*;
import static xyz.iwasacar.api.domain.products.service.ProductService.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.component.AwsS3Uploader;
import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.caroptions.entity.CarOption;
import xyz.iwasacar.api.domain.caroptions.repository.CarOptionRepository;
import xyz.iwasacar.api.domain.histories.entity.SaleHistory;
import xyz.iwasacar.api.domain.histories.repository.SaleHistoryRepository;
import xyz.iwasacar.api.domain.labels.entity.Label;
import xyz.iwasacar.api.domain.labels.repository.LabelRepository;
import xyz.iwasacar.api.domain.products.dto.response.AdminProductUpdateResponse;
import xyz.iwasacar.api.domain.products.dto.response.ProductDetailResponse;
import xyz.iwasacar.api.domain.products.dto.response.ProductResponse;
import xyz.iwasacar.api.domain.products.dto.response.ProductSaleDetailResponse;
import xyz.iwasacar.api.domain.products.entity.Product;
import xyz.iwasacar.api.domain.products.exception.ProductNotFound;
import xyz.iwasacar.api.domain.products.repository.ProductImageRepository;
import xyz.iwasacar.api.domain.products.repository.ProductRepository;
import xyz.iwasacar.api.domain.resources.entity.ProductImage;
import xyz.iwasacar.api.domain.resources.entity.Resource;
import xyz.iwasacar.api.domain.resources.repository.ResourceRepository;
import xyz.iwasacar.api.domain.roles.entity.Role;
import xyz.iwasacar.api.domain.roles.entity.RoleName;
import xyz.iwasacar.api.domain.roles.exception.RoleNotFoundException;
import xyz.iwasacar.api.domain.roles.repository.RoleRepository;

@Service(ADMIN_PRODUCT_SERVICE)
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminProductService implements ProductService {

	private final ProductRepository productRepository;
	private final ProductImageRepository productImageRepository;
	private final ResourceRepository resourceRepository;
	private final CarOptionRepository carOptionRepository;
	private final RoleRepository roleRepository;
	private final SaleHistoryRepository saleHistoryRepository;
	private final LabelRepository labelRepository;
	private final AwsS3Uploader uploader;

	@Override
	public ProductDetailResponse findProductDetail(final Long id) {

		Product productDetail = productRepository.findProductDetailForAdmin(id)
			.orElseThrow(ProductNotFound::new);

		Map<String, List<String>> carOptionGroup =
			CarOption.convertCarOption(carOptionRepository.findOptionsByProductId(id));

		List<String> resources = resourceRepository.findByProductIdAdmin(id)
			.stream()
			.map(Resource::getUrl)
			.collect(toList());

		return ProductDetailResponse.of(productDetail, resources, carOptionGroup);
	}

	@Override
	public PageResponse<ProductResponse> findWaitingProducts(Integer page, Integer size) {
		return PageResponse.of(productRepository.findWaitingProducts(page, size));
	}

	@Override
	public PageResponse<ProductResponse> findProducts(final Integer page, final Integer size) {

		return PageResponse.of(productRepository.findProductsForAdmin(page, size));
	}

	@Transactional
	@Override
	public ProductDetailResponse updateProduct(
		final Long productId, final MultipartFile performanceCheck, final List<MultipartFile> images
	) {
		Product product = productRepository.getBy(productId);
		Role adminRole = roleRepository.findByName(RoleName.ADMIN)
			.orElseThrow(RoleNotFoundException::new);

		String url = uploader.upload(performanceCheck, PERFORMANCE_CHECK);
		Resource savedPerformanceCheck = resourceRepository.save(
			new Resource(url, performanceCheck.getOriginalFilename()));

		product.addPerformanceCheck(savedPerformanceCheck);

		List<Resource> imgs = images.stream()
			.map(i -> new Resource(uploader.upload(i, IMAGES), i.getOriginalFilename()))
			.collect(toList());

		List<ProductImage> productImages = resourceRepository.saveAll(imgs)
			.stream()
			.map(r -> new ProductImage(product, r, adminRole))
			.collect(toList());

		productImageRepository.saveAll(productImages);

		List<String> imageUrls = productImages.stream()
			.map(p -> p.getResource().getUrl())
			.collect(toList());

		Map<String, List<String>> carOptionGroup =
			CarOption.convertCarOption(carOptionRepository.findOptionsByProductId(productId));

		return ProductDetailResponse.of(product, imageUrls, carOptionGroup);
	}

	@Transactional
	@Override
	public void deleteProduct(final Long productId) {
		productRepository.getBy(productId).delete();
	}

	@Override
	public List<ProductResponse> findProducts(Long category, String keyword, Long lastProductId) {
		throw new IllegalArgumentException();
	}

	@Override
	public List<ProductResponse> findSpecificProducts(Long carType, Integer capital, Integer loan, Long lastProductId) {
		throw new IllegalArgumentException();
	}

	@Override
	public ProductSaleDetailResponse findProductHistory(Long productId) {
		SaleHistory saleHistory = saleHistoryRepository.findWithMemberAndProductByProductId(productId);
		return ProductSaleDetailResponse.of(saleHistory);
	}

	@Transactional
	@Override
	public String addPerformanceCheck(final Long productId, final MultipartFile performanceCheck) {
		Product product = productRepository.getBy(productId);
		String performanceCheckUrl = uploader.upload(performanceCheck, PERFORMANCE_CHECK);
		Resource savedPerformanceCheck = resourceRepository.save(new Resource(performanceCheckUrl, performanceCheck.getOriginalFilename()));
		product.addPerformanceCheck(savedPerformanceCheck);

		return performanceCheckUrl;
	}

	@Transactional
	@Override
	public List<String> addAdminImages(Long productId, List<MultipartFile> images) {
		Product product = productRepository.getBy(productId);
		Role adminRole = roleRepository.findByName(RoleName.ADMIN).orElseThrow(RoleNotFoundException::new);

		List<ProductImage> productImages = new ArrayList<>();
		List<Resource> resources = images.stream()
			.map(image -> {
				String imageUrl = uploader.upload(image, IMAGES);
				Resource resource = new Resource(imageUrl, image.getOriginalFilename());
				productImages.add(new ProductImage(product, resource, adminRole));
				return resource;
			}).collect(toList());

		List<Resource> savedResources = resourceRepository.saveAll(resources);
		productImageRepository.saveAll(productImages);

		return savedResources.stream()
			.map(Resource::getUrl)
			.collect(toList());
	}

	@Transactional
	@Override
	public AdminProductUpdateResponse updatePriceAndLabel(
		final Long productId, final Integer price, final Long labelId) {

		Product product = productRepository.getBy(productId);
		Label label = labelRepository.getBy(labelId);

		product.updateLabel(label);
		product.updatePrice(price);

		return new AdminProductUpdateResponse(price, label.getId(), label.getName());
	}

}
