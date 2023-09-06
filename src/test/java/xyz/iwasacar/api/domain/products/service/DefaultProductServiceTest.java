package xyz.iwasacar.api.domain.products.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import xyz.iwasacar.api.domain.caroptions.entity.CarOption;
import xyz.iwasacar.api.domain.caroptions.repository.CarOptionRepository;
import xyz.iwasacar.api.domain.products.dto.response.ProductDetailResponse;
import xyz.iwasacar.api.domain.products.dto.response.ProductResponse;
import xyz.iwasacar.api.domain.products.entity.Product;
import xyz.iwasacar.api.domain.products.repository.ProductRepository;
import xyz.iwasacar.api.domain.resources.entity.ProductImage;
import xyz.iwasacar.api.domain.resources.entity.Resource;
import xyz.iwasacar.api.domain.resources.repository.ResourceRepository;
import xyz.iwasacar.api.dummy.Dummy;

@ExtendWith(MockitoExtension.class)
class DefaultProductServiceTest {

	@Mock
	ProductRepository productRepository;

	@Mock
	CarOptionRepository carOptionRepository;

	@Mock
	ResourceRepository resourceRepository;

	@InjectMocks
	DefaultProductService productService;

	@DisplayName("상품 상세 조회")
	@Test
	void testFindProductDetail() {
		int times = 10;
		Long productId = 1L;
		Product product = Dummy.getProduct(Dummy.getCarTypeDummy(), Dummy.getColor(), Dummy.getLabel(),
			Dummy.getBrand(), Dummy.getPerformanceCheck());
		List<CarOption> carOptions = new ArrayList<>();
		List<Resource> resources = new ArrayList<>();

		given(productRepository.findProductDetail(productId)).willReturn(Optional.of(product));

		for (int i = 0; i < times; i++) {
			CarOption option = mock(CarOption.class);
			given(option.getType()).willReturn("type" + i);
			given(option.getName()).willReturn("carOptionName" + i);

			Resource resource = mock(Resource.class);
			given(resource.getUrl()).willReturn("url" + i);

			carOptions.add(option);
			resources.add(resource);
		}

		given(carOptionRepository.findOptionsByProductId(productId)).willReturn(carOptions);
		given(resourceRepository.findByProductId(productId)).willReturn(resources);

		ProductDetailResponse productDetail = productService.findProductDetail(productId);

		assertThat(productDetail.getOptions()).hasSize(times);
		assertThat(productDetail.getImages()).hasSize(times);

		then(resourceRepository).should(times(1)).findByProductId(productId);
		then(productRepository).should(times(1)).findProductDetail(productId);
		then(carOptionRepository).should(times(1)).findOptionsByProductId(productId);
	}

	@DisplayName("상품 목록 조회 10개씩")
	@Test
	void testFindProducts() {
		int times = 10;
		Long carType = null;
		String keyword = null;
		Long lastProductId = 11L;
		List<ProductImage> productImageList = new ArrayList<>();

		for (int i = 0; i < times; i++) {
			Product product = Dummy.getProduct(Dummy.getCarTypeDummy(), Dummy.getColor(), Dummy.getLabel(),
				Dummy.getBrand(), Dummy.getPerformanceCheck());

			productImageList.add(Dummy.getProductImage(product, Dummy.getResource(),
				Dummy.getAdminRole()));

		}

		given(resourceRepository.findByProducts(carType, keyword, lastProductId)).willReturn(productImageList);

		List<ProductResponse> products = productService.findProducts(carType, keyword, lastProductId);

		assertThat(products).hasSize(times);

		then(resourceRepository).should(times(1)).findByProducts(carType, keyword, lastProductId);
	}

}
