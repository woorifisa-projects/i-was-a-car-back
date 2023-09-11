package xyz.iwasacar.api.dummy;

import static org.springframework.test.util.ReflectionTestUtils.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import xyz.iwasacar.api.domain.histories.dto.request.ProductCreateRequest;
import xyz.iwasacar.api.domain.histories.dto.response.CarInfoResponse;
import xyz.iwasacar.api.domain.histories.dto.response.PurchaseHistoryResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleHistoryDetailResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleHistoryResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleResponse;
import xyz.iwasacar.api.domain.labels.entity.LabelName;

public class DtoDummy {

	public static ProductCreateRequest productCreateRequest() {
		ProductCreateRequest productCreateRequest = new ProductCreateRequest();

		Long id = 1L;

		setField(productCreateRequest, "carTypeId", id);
		setField(productCreateRequest, "brandId", id);
		setField(productCreateRequest, "colorId", id);
		setField(productCreateRequest, "bankId", id);
		setField(productCreateRequest, "year", LocalDate.now());
		setField(productCreateRequest, "distance", 10_000);
		setField(productCreateRequest, "meetingSchedule", LocalDateTime.now());
		setField(productCreateRequest, "memberName", "황동민");
		setField(productCreateRequest, "carName", "SONATA");
		setField(productCreateRequest, "price", 10_000_000);
		setField(productCreateRequest, "info", "123가1234");
		setField(productCreateRequest, "transmission", "자동");
		setField(productCreateRequest, "fuel", "가솔린");
		setField(productCreateRequest, "drivingMethod", "전륜");
		setField(productCreateRequest, "zipCode", "12345");
		setField(productCreateRequest, "address", "서울");
		setField(productCreateRequest, "addressDetail", "1234");
		setField(productCreateRequest, "inundationHistory", false);
		setField(productCreateRequest, "accidentHistory", 0);
		setField(productCreateRequest, "displacement", 1999.0);
		setField(productCreateRequest, "fuelEfficiency", 12.5);
		setField(productCreateRequest, "accountNumber", "12341234");
		setField(productCreateRequest, "accountHolder", "황동민");
		setField(productCreateRequest, "options", Map.of("내장", List.of("좋은 옵션")));

		return productCreateRequest;
	}

	public static PurchaseHistoryResponse purchaseHistoryResponse() {
		return PurchaseHistoryResponse.builder()
			.id(1L)
			.memberName("황동민")
			.productId(1L)
			.productName("SONATA")
			.bankName("은행")
			.loanName("대출1")
			.insuranceName("보험1")
			.zipCode("12345")
			.address("서울시")
			.addressDetail("123")
			.accountNumber("12341234")
			.accountHolder("황동민")
			.deliverySchedule(LocalDateTime.now())
			.createAt(LocalDateTime.now())
			.build();
	}

	public static SaleResponse saleResponse() {
		final Long id = 1L;

		return SaleResponse.builder()
			.saleId(id)
			.memberName("황동민")
			.productId(id)
			.productName("SONATA")
			.carType("중형차")
			.brand("현대")
			.label(LabelName.심사완료)
			.color("검정")
			.fakeProductStatus(false)
			.info("123가1234")
			.transmission("자동")
			.fuel("가솔린")
			.drivingMethod("전륜")
			.year(LocalDate.now())
			.price(10_000_000)
			.fuelEfficiency(10.2)
			.accidentHistory(3)
			.inundationHistory(false)
			.bankName("은행")
			.accountNumber("12341234")
			.accountHolder("황동민")
			.meetingSchedule(LocalDateTime.now())
			.zipCode("12345")
			.address("seoul")
			.addressDetail("1234")
			.createdAt(LocalDateTime.now())
			.images(List.of("url1"))
			.options(Map.of("옵션1", List.of("Good")))
			.build();
	}

	public static CarInfoResponse carInfoResponse() {
		CarInfoResponse response = new CarInfoResponse();

		Long id = 1L;

		setField(response, "carTypeId", id);
		setField(response, "brandId", id);
		setField(response, "colorId", id);

		setField(response, "memberName", "황동민");
		setField(response, "carName", "SONATA");
		setField(response, "brand", "현대");
		setField(response, "carType", "중형차");
		setField(response, "color", "검정");
		setField(response, "info", "123가1234");
		setField(response, "year", LocalDate.now());
		setField(response, "info", "123가1234");
		setField(response, "transmission", "자동");
		setField(response, "fuel", "가솔린");
		setField(response, "drivingMethod", "전륜");
		setField(response, "inundationHistory", false);
		setField(response, "accidentHistory", 0);
		setField(response, "displacement", 1999.0);
		setField(response, "fuelEfficiency", 12.5);
		setField(response, "options", Map.of("내장", List.of("좋은 옵션")));

		return response;
	}

	public static SaleHistoryDetailResponse saleHistoryDetailResponse() {
		return SaleHistoryDetailResponse
			.builder()
			.sellerName("황동민")
			.sellerTel("1234")
			.zipCode("12345")
			.address("서울시")
			.meetingSchedule(LocalDateTime.now())
			.productName("SONATA")
			.productPrice(10_000_000)
			.bankName("은행")
			.accountHolder("황동민")
			.accountNum("123412341234")
			.labelName(LabelName.심사완료)
			.build();
	}

	public static SaleHistoryResponse saleHistoryResponse() {
		return new SaleHistoryResponse(1L, "SONATA", LabelName.심사완료, LocalDateTime.now());
	}

	public static SaleHistoryResponse saleHistoryResponse(final Long id) {
		return new SaleHistoryResponse(id, "SONATA", LabelName.심사완료, LocalDateTime.now());
	}

}
