package xyz.iwasacar.api.domain.histories.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SaleRequest {

	private Long carTypeId;
	private Long brandId;
	private Long colorId;
	private Long bankId;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate year;
	private String name;
	private Integer price;
	private String info;
	private String transmission;
	private String fuel;
	private String drivingMethod;
	private Integer distance;
	private Double fuelEfficiency;
	private Double displacement;
	private Integer accidentHistory;
	private Boolean inundationHistory;

	private String zipCode;
	private String address;
	private String addressDetail;
	private String accountNumber;
	private String accountHolder;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime deliverySchedule;

	private List<Long> carOptions;

}
