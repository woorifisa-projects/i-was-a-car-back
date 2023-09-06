package xyz.iwasacar.api.domain.histories.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CarInfoResponse {

	private Long carTypeId;
	private Long brandId;
	private Long colorId;
	
	private String memberName;
	private String carName;
	private String carType;
	private String brand;
	private String color;
	private String info;
	private String transmission;
	private String fuel;
	private String drivingMethod;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate year;
	private Double fuelEfficiency;
	private Double displacement;
	private Integer accidentHistory;
	private Boolean inundationHistory;
	private List<String> images;
	private Map<String, List<String>> options;

}
