package xyz.iwasacar.api.dummy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.test.util.ReflectionTestUtils;

import xyz.iwasacar.api.domain.histories.dto.request.ProductCreateRequest;

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

}
