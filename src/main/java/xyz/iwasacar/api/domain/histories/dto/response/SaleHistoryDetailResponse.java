package xyz.iwasacar.api.domain.histories.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.histories.entity.SaleHistory;
import xyz.iwasacar.api.domain.labels.entity.LabelName;
import xyz.iwasacar.api.domain.products.entity.Product;
import xyz.iwasacar.api.domain.resources.entity.ProductImage;

@RequiredArgsConstructor
@Builder
@Getter
public class SaleHistoryDetailResponse {

	private final Long id;

	private final String bank; // 은행명

	private final String accountHolder; // 예금주

	private final String accountNumber; // 계좌번호

	private final String productName; //  상품명

	private final String brand; // 제조사명

	private final String carType; // 차종

	private final Integer price; // 가격

	private final Integer distance; // 주행거리

	private final LocalDate year; // 연식

	private final LabelName label; // 라벨명

	private final LocalDateTime meetingSchedule; // 미팅날짜

	private final Integer zipCode; // 우편번호

	private final String address; // 주소

	private final String addressDetail; // 상세주소

	private final String productImageUrl; // 차량 이미지

	public static SaleHistoryDetailResponse from(SaleHistory saleHistory, Product product, ProductImage productImage) {

		return SaleHistoryDetailResponse.builder()
			.id(saleHistory.getId())
			.bank(saleHistory.getBank().getName())
			.accountHolder(saleHistory.getAccountHolder())
			.accountNumber(saleHistory.getAccountNumber())
			.address(saleHistory.getAddress())
			.addressDetail(saleHistory.getAddressDetail())
			.meetingSchedule(saleHistory.getMeetingSchedule())
			.zipCode(saleHistory.getZipCode())

			.productName(product.getName())
			.year(product.getYear())
			.distance(product.getDistance())
			.price(product.getPrice())
			.brand(product.getBrand().getName())
			.carType(product.getCarType().getName())
			.label(product.getLabel().getName())

			.productImageUrl(productImage.getResource().getUrl())
			.build();
	}

}
