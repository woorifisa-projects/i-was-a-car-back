package xyz.iwasacar.api.domain.histories.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

	private String memberName;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate year;
	private String carName;
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
	private LocalDateTime meetingSchedule;

	private List<Long> carOptions;

	public boolean isOldCar(final LocalDate today) {
		return today.getYear() - this.year.getYear() >= 12;
	}

	public boolean isDistanceTooShort() {
		return this.distance <= 5_000;
	}

	public boolean isBusyCustomer(final LocalDate today) {
		int year = this.meetingSchedule.getYear();
		int month = this.meetingSchedule.getMonthValue();
		int day = this.meetingSchedule.getDayOfMonth();

		LocalDate meetingDate = LocalDate.of(year, month, day);
		LocalDate tomorrow = today.plusDays(1L);

		return Objects.equals(meetingDate, tomorrow);
	}

}
