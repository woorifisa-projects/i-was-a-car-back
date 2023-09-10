package xyz.iwasacar.api.dummy;

import java.time.LocalDate;

import org.springframework.test.util.ReflectionTestUtils;

import xyz.iwasacar.api.domain.banks.entity.Bank;
import xyz.iwasacar.api.domain.brands.entity.Brand;
import xyz.iwasacar.api.domain.cartypes.entity.CarType;
import xyz.iwasacar.api.domain.colors.entity.Color;
import xyz.iwasacar.api.domain.common.constant.EntityStatus;
import xyz.iwasacar.api.domain.insurances.entity.Insurance;
import xyz.iwasacar.api.domain.labels.entity.Label;
import xyz.iwasacar.api.domain.labels.entity.LabelName;
import xyz.iwasacar.api.domain.products.entity.Product;
import xyz.iwasacar.api.domain.resources.entity.ProductImage;
import xyz.iwasacar.api.domain.resources.entity.Resource;
import xyz.iwasacar.api.domain.roles.entity.Role;
import xyz.iwasacar.api.domain.roles.entity.RoleName;

public class Dummy {

	public static CarType getCarTypeDummy() {
		return new CarType("SUV");
	}

	public static CarType getCarTypeDummy(String name) {
		return new CarType(name);
	}

	public static Color getColor() {
		return new Color("파랑");
	}

	public static Color getColor(String name) {
		return new Color(name);
	}

	public static Label getLabel() {
		return new Label(LabelName.심사완료);
	}

	public static Label getLabel(LabelName name) {
		return new Label(name);
	}

	public static Brand getBrand() {
		return new Brand("현대");
	}

	public static Brand getBrand(String name) {
		return new Brand(name);
	}

	public static Role getAdminRole() {
		return new Role(RoleName.ADMIN);
	}

	public static Role getMemberRole() {
		return new Role(RoleName.MEMBER);
	}

	public static Resource getPerformanceCheck() {
		return Resource.builder()
			.originalName("performanceCheck")
			.status(EntityStatus.CREATED)
			.url("www.google.com")
			.build();
	}

	public static Resource getPerformanceCheck(String url) {
		return Resource.builder()
			.originalName("performanceCheck")
			.status(EntityStatus.CREATED)
			.url(url)
			.build();
	}

	public static Resource getResource() {
		return Resource.builder()
			.originalName("resource")
			.status(EntityStatus.CREATED)
			.url("www.google.com")
			.build();
	}

	public static ProductImage getProductImage(Product product, Resource resource, Role role) {

		return ProductImage.builder()
			.product(product)
			.resource(resource)
			.role(role)
			.build();
	}

	public static Product getProduct(CarType carType, Color color, Label label, Brand brand,
		Resource performanceCheck) {
		Product product = Product.builder()
			.carType(carType)
			.color(color)
			.label(label)
			.brand(brand)
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
			.build();

		ReflectionTestUtils.setField(product, "performanceCheck", performanceCheck);

		return product;
	}

	public static Bank getBank() {
		return Bank.builder().name("우리 은행").build();
	}

	public static Insurance getInsurance() {
		return Insurance.builder()
			.name("삼성화재 다이렉트 운전자보험")
			.company("삼성화재")
			.period(12)
			.monthlyPremium(120000)
			.build();

	}

}
