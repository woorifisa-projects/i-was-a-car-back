package xyz.iwasacar.api.dummy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.test.util.ReflectionTestUtils;

import xyz.iwasacar.api.domain.histories.dto.request.ProductCreateRequest;
import xyz.iwasacar.api.domain.histories.dto.response.PurchaseHistoryResponse;

public class DtoDummy {

	public static ProductCreateRequest productCreateRequest() {
		ProductCreateRequest productCreateRequest = new ProductCreateRequest();

		Long id = 1L;

		ReflectionTestUtils.setField(productCreateRequest, "carTypeId", id);
		ReflectionTestUtils.setField(productCreateRequest, "brandId", id);
		ReflectionTestUtils.setField(productCreateRequest, "colorId", id);
		ReflectionTestUtils.setField(productCreateRequest, "bankId", id);
		ReflectionTestUtils.setField(productCreateRequest, "options", Map.of());
		ReflectionTestUtils.setField(productCreateRequest, "year", LocalDate.now());
		ReflectionTestUtils.setField(productCreateRequest, "distance", 10_000);
		ReflectionTestUtils.setField(productCreateRequest, "meetingSchedule", LocalDateTime.now());
		// ReflectionTestUtils.setField(productCreateRequest, , );
		// ReflectionTestUtils.setField(productCreateRequest, , );

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

}
