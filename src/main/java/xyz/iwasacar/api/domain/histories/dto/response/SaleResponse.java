package xyz.iwasacar.api.domain.histories.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.histories.entity.SaleHistory;
import xyz.iwasacar.api.domain.labels.entity.LabelName;
import xyz.iwasacar.api.domain.members.entity.Member;
import xyz.iwasacar.api.domain.products.entity.Product;

@Builder
@RequiredArgsConstructor
@Getter
public class SaleResponse {

	private final Long saleId;
	private final String memberName;

	private final Long productId;
	private final String productName;
	private final String carType;
	private final String brand;
	private final LabelName label;
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

	public static SaleResponse from(Member member, Product product, SaleHistory saleHistory,
		List<String> images, Map<String, List<String>> options) {
		return SaleResponse
			.builder()
			.saleId(saleHistory.getId())
			.memberName(member.getName())
			.productId(product.getId())
			.productName(product.getName())
			.carType(product.getCarType().getName())
			.bankName(product.getBrand().getName())
			.label(product.getLabel().getName())
			.color(product.getColor().getName())
			.fakeProductStatus(product.getFakeProductStatus())
			.info(product.getInfo())
			.transmission(product.getTransmission())
			.fuel(product.getFuel())
			.drivingMethod(product.getDrivingMethod())
			.year(product.getYear())
			.distance(product.getDistance())
			.price(product.getPrice())
			.fuelEfficiency(product.getFuelEfficiency())
			.displacement(product.getDisplacement())
			.accidentHistory(product.getAccidentHistory())
			.inundationHistory(product.getInundationHistory())
			.bankName(saleHistory.getBank().getName())
			.accountNumber(saleHistory.getAccountNumber())
			.accountHolder(saleHistory.getAccountHolder())
			.meetingSchedule(saleHistory.getMeetingSchedule())
			.zipCode(saleHistory.getZipCode())
			.address(saleHistory.getAddress())
			.addressDetail(saleHistory.getAddressDetail())
			.createdAt(saleHistory.getCreateAt())
			.images(images)
			.options(options)
			.build();
	}

}
