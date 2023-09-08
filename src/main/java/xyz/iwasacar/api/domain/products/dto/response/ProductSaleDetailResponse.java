package xyz.iwasacar.api.domain.products.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.histories.dto.response.HistoryAdminResponse;
import xyz.iwasacar.api.domain.labels.entity.LabelName;
import xyz.iwasacar.api.domain.products.entity.Product;

@RequiredArgsConstructor
@Getter
public class ProductSaleDetailResponse {

	private final Long id;
	private final String name;
	private final String carType;
	private final String brand;
	private final LabelName label;
	private final String performanceCheck;
	private final String color;
	private final Boolean fakeProductStatus;
	private final String info;
	private final String transmission;
	private final String fuel;
	private final String drivingMethod;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private final LocalDate year;
	private final Integer distance;
	private final Integer price;
	private final Double fuelEfficiency;
	private final Double displacement;
	private final Integer accidentHistory;
	private final Boolean inundationHistory;


	private final String memberName;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private final LocalDateTime saleMeeting;
	private final String address;
	private final String addressDetail;


	public static ProductSaleDetailResponse of(Product product, HistoryAdminResponse historyAdminResponse) {
		return new ProductSaleDetailResponse(
			product.getId(),
			product.getName(),
			product.getCarType().getName(),
			product.getBrand().getName(),
			product.getLabel().getName(),
			product.getPerformanceCheck().getUrl(),
			product.getColor().getName(),
			product.getFakeProductStatus(),
			product.getInfo(),
			product.getTransmission(),
			product.getFuel(),
			product.getDrivingMethod(),
			product.getYear(), product.getDistance(), product.getPrice(),
			product.getFuelEfficiency(), product.getDisplacement(), product.getAccidentHistory(),
			product.getInundationHistory(),
			historyAdminResponse.getMemberName(),
			historyAdminResponse.getMeetingSchedule(),
			historyAdminResponse.getAddress(),
			historyAdminResponse.getAddressDetail()
		);
	}
}
