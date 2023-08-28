package xyz.iwasacar.api.domain.histories.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.histories.entity.SaleHistory;
import xyz.iwasacar.api.domain.labels.entity.LabelName;
import xyz.iwasacar.api.domain.members.entity.Member;
import xyz.iwasacar.api.domain.products.entity.Product;

@RequiredArgsConstructor
@Getter
public class SaleResponse {

	private final String memberName;

	private final Long productId;
	private final String productName;
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

	private final String bankName;
	private final String accountNumber;
	private final String accountHolder;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private final LocalDateTime meetingSchedule;
	private final String zipCode;
	private final String address;
	private final String addressDetail;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private final LocalDateTime createdAt;

	private final List<String> images;
	private final Map<String, List<String>> options;

	public SaleResponse(Member member, Product product, SaleHistory saleHistory,
		List<String> images, Map<String, List<String>> options) {

		this(
			member.getName(),

			product.getId(), product.getName(), product.getCarType().getName(), product.getBrand().getName(),
			product.getLabel().getName(), product.getPerformanceCheck().getUrl(), product.getColor().getName(),
			product.getFakeProductStatus(), product.getInfo(), product.getTransmission(), product.getFuel(),
			product.getDrivingMethod(), product.getYear(), product.getDistance(), product.getPrice(),
			product.getFuelEfficiency(), product.getDisplacement(), product.getAccidentHistory(),
			product.getInundationHistory(),

			saleHistory.getBank().getName(), saleHistory.getAccountNumber(), saleHistory.getAccountHolder(),
			saleHistory.getMeetingSchedule(),
			saleHistory.getZipCode(), saleHistory.getAddress(), saleHistory.getAddressDetail(),
			saleHistory.getCreateAt(),

			images, options
		);
	}

}
