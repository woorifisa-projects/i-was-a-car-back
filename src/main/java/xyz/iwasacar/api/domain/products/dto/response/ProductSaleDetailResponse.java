package xyz.iwasacar.api.domain.products.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.histories.entity.SaleHistory;
import xyz.iwasacar.api.domain.labels.entity.LabelName;

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

	public static ProductSaleDetailResponse of(SaleHistory saleHistory) {
		return new ProductSaleDetailResponse(
			saleHistory.getProduct().getId(),
			saleHistory.getProduct().getName(),
			saleHistory.getProduct().getCarType().getName(),
			saleHistory.getProduct().getBrand().getName(),
			saleHistory.getProduct().getLabel().getName(),
			saleHistory.getProduct().getPerformanceCheck() == null ? null :
				saleHistory.getProduct().getPerformanceCheck().getUrl(),
			saleHistory.getProduct().getColor().getName(),
			saleHistory.getProduct().getFakeProductStatus(),
			saleHistory.getProduct().getInfo(),
			saleHistory.getProduct().getTransmission(),
			saleHistory.getProduct().getFuel(),
			saleHistory.getProduct().getDrivingMethod(),
			saleHistory.getProduct().getYear(), saleHistory.getProduct().getDistance(),
			saleHistory.getProduct().getPrice(),
			saleHistory.getProduct().getFuelEfficiency(), saleHistory.getProduct().getDisplacement(),
			saleHistory.getProduct().getAccidentHistory(),
			saleHistory.getProduct().getInundationHistory(),
			saleHistory.getMember().getName(),
			saleHistory.getMeetingSchedule(),
			saleHistory.getAddress(),
			saleHistory.getAddressDetail()
		);
	}
}
