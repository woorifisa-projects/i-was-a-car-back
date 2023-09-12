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
import xyz.iwasacar.api.domain.members.dto.request.LoginRequest;
import xyz.iwasacar.api.domain.members.dto.request.SignupRequest;
import xyz.iwasacar.api.domain.members.dto.request.UpdateRequest;
import xyz.iwasacar.api.domain.members.dto.response.MemberResponse;
import xyz.iwasacar.api.domain.members.dto.response.MemberUpdateResponse;
import xyz.iwasacar.api.domain.members.entity.Gender;
import xyz.iwasacar.api.domain.roles.entity.RoleName;

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

	public static MemberResponse memberResponse() {
		return MemberResponse.builder()
			.id(1L)
			.email("admin@iwasacar.xyz")
			.name("admin")
			.tel("010-1234-1234")
			.gender(Gender.남자)
			.hasLicense(true)
			.roles(List.of(RoleName.ADMIN))
			.birth(LocalDate.now())
			.build();
	}

	public static MemberResponse memberResponse(final Long id) {
		return MemberResponse.builder()
			.id(id)
			.email("admin@iwasacar.xyz")
			.name("admin")
			.tel("010-1234-1234")
			.gender(Gender.남자)
			.hasLicense(true)
			.roles(List.of(RoleName.ADMIN))
			.birth(LocalDate.now())
			.build();
	}

	public static SignupRequest signupRequest() {
		SignupRequest request = new SignupRequest();

		setField(request, "email", "admin@iwasacar.xyz");
		setField(request, "code", "abcdefg");
		setField(request, "password", "pa$$w0rd");
		setField(request, "name", "황동민");
		setField(request, "tel", "010-1234-1234");
		setField(request, "gender", Gender.남자);
		setField(request, "hasLicense", true);
		setField(request, "birth", LocalDate.of(1997, 9, 4));

		return request;
	}

	public static LoginRequest loginRequest() {
		LoginRequest request = new LoginRequest();

		setField(request, "email", "admin@iwasacar.xyz");
		setField(request, "password", "pa$$w0rd");

		return request;
	}

	public static MemberUpdateResponse memberUpdateResponse() {
		return MemberUpdateResponse.builder()
			.name("황동민")
			.password("pa$$w0rd")
			.tel("010-1234-1234")
			.gender(Gender.남자)
			.hasLicense(true)
			.lastLoginAt(LocalDateTime.now())
			.build();
	}

	public static UpdateRequest updateRequest() {
		UpdateRequest request = new UpdateRequest();

		setField(request, "name", "황동민");
		setField(request, "password", "pa$$w0rd");
		setField(request, "name", "황동민");
		setField(request, "tel", "010-1234-1234");
		setField(request, "gender", Gender.남자);
		setField(request, "hasLicense", true);

		return request;
	}

}
