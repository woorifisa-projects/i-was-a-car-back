package xyz.iwasacar.api.domain.resources.repository;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import xyz.iwasacar.api.config.TestConfig;
import xyz.iwasacar.api.domain.brands.entity.Brand;
import xyz.iwasacar.api.domain.brands.repository.BrandRepository;
import xyz.iwasacar.api.domain.cartypes.entity.CarType;
import xyz.iwasacar.api.domain.cartypes.repository.CarTypeRepository;
import xyz.iwasacar.api.domain.colors.entity.Color;
import xyz.iwasacar.api.domain.colors.repository.ColorRepository;
import xyz.iwasacar.api.domain.common.constant.EntityStatus;
import xyz.iwasacar.api.domain.labels.entity.Label;
import xyz.iwasacar.api.domain.labels.entity.LabelName;
import xyz.iwasacar.api.domain.labels.repository.LabelRepository;
import xyz.iwasacar.api.domain.products.entity.Product;
import xyz.iwasacar.api.domain.products.repository.ProductImageRepository;
import xyz.iwasacar.api.domain.products.repository.ProductRepository;
import xyz.iwasacar.api.domain.resources.entity.ProductImage;
import xyz.iwasacar.api.domain.resources.entity.Resource;
import xyz.iwasacar.api.domain.roles.entity.Role;
import xyz.iwasacar.api.domain.roles.entity.RoleName;
import xyz.iwasacar.api.domain.roles.repository.RoleRepository;
import xyz.iwasacar.api.dummy.Dummy;

@DataJpaTest
@Import(TestConfig.class)
class ResourceRepositoryTest {

	@Autowired
	ResourceRepository resourceRepository;

	@Autowired
	ColorRepository colorRepository;

	@Autowired
	CarTypeRepository carTypeRepository;

	@Autowired
	LabelRepository labelRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	BrandRepository brandRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	ProductImageRepository productImageRepository;

	@DisplayName("상품을 통해 자원 찾기")
	@Test
	void testFindByProductId() {
		CarType carType = new CarType("SUV");
		Color color = new Color("파랑");
		Label label = new Label(LabelName.심사완료);
		Brand brand = new Brand("현대");
		Role role = new Role(RoleName.ADMIN);

		Resource performanceCheck = Resource.builder()
			.originalName("performanceCheck")
			.status(EntityStatus.CREATED)
			.url("www.google.com")
			.build();

		Resource resource = Resource.builder()
			.originalName("hello")
			.status(EntityStatus.CREATED)
			.url("www.google.com")
			.build();

		Product product = Product.builder()
			.carType(carType)
			.color(color)
			.label(label)
			.brand(brand)
			.performanceCheck(performanceCheck)
			.name("소나타")
			.fakeProductStatus(false)
			.transmission("자동")
			.fuel("가솔린")
			.year(LocalDate.now())
			.price(20_000_000)
			.fuelEfficiency(10.1)
			.displacement(3000.0)
			.distance(10_000)
			.info("123가4567")
			.drivingMethod("전륜")
			.accidentHistory(1)
			.inundationHistory(false)
			.status(EntityStatus.CREATED)
			.build();

		ProductImage productImage = new ProductImage(product, resource, role);

		carTypeRepository.save(carType);
		colorRepository.save(color);
		brandRepository.save(brand);
		resourceRepository.save(resource);
		labelRepository.save(label);
		resourceRepository.save(performanceCheck);
		productRepository.save(product);
		roleRepository.save(role);
		productImageRepository.save(productImage);

		List<Resource> resources = resourceRepository.findByProductId(1L);

		assertThat(resources).hasSize(1);
	}

	@DisplayName("상품 목록 전체 10개씩 찾기 ")
	@Test
	void testFindByProducts() {

		List<CarType> carTypes = new ArrayList<>();
		List<Color> colors = new ArrayList<>();
		List<Label> labels = new ArrayList<>();
		List<Brand> brands = new ArrayList<>();
		List<Role> roles = new ArrayList<>();

		List<Resource> performanceChecks = new ArrayList<>();
		List<Resource> resources = new ArrayList<>();
		List<Product> products = new ArrayList<>();
		List<ProductImage> productImages = new ArrayList<>();

		Role role = Dummy.getAdminRole();
		roles.add(role);

		for (int i = 1; i < 11; i++) {
			CarType carType = Dummy.getCarTypeDummy();
			Color color = Dummy.getColor();
			Label label = Dummy.getLabel();
			Brand brand = Dummy.getBrand();

			carTypes.add(carType);
			colors.add(color);
			labels.add(label);
			brands.add(brand);

			Resource performanceCheck = Dummy.getPerformanceCheck();
			Resource resource = Dummy.getResource();
			Product product = Dummy.getProduct(carType, color, label, brand, performanceCheck);

			performanceChecks.add(performanceCheck);
			resources.add(resource);
			products.add(product);
			productImages.add(Dummy.getProductImage(product, resource, role));

		}

		carTypeRepository.saveAll(carTypes);
		colorRepository.saveAll(colors);
		brandRepository.saveAll(brands);
		resourceRepository.saveAll(resources);
		labelRepository.saveAll(labels);
		resourceRepository.saveAll(performanceChecks);
		productRepository.saveAll(products);
		roleRepository.saveAll(roles);
		productImageRepository.saveAll(productImages);

		List<ProductImage> productImagesRes = resourceRepository.findByProducts(11L);
		assertThat(productImagesRes).hasSize(10);
	}

}
