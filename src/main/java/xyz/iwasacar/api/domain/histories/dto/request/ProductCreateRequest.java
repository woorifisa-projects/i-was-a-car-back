package xyz.iwasacar.api.domain.histories.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class ProductCreateRequest {

	@NotNull
	private Long carTypeId;
	@NotNull
	private Long brandId;
	@NotNull
	private Long colorId;
	@NotNull
	private Long bankId;

	@NotNull
	private String memberName;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private LocalDate year;
	@NotNull
	private String carName;
	@NotNull
	private Integer price;
	@NotNull
	private String info;
	@NotNull
	private String transmission;
	@NotNull
	private String fuel;
	@NotNull
	private String drivingMethod;
	@NotNull
	private Integer distance;
	@NotNull
	private Double fuelEfficiency;
	@NotNull
	private Double displacement;
	@NotNull
	private Integer accidentHistory;
	@NotNull
	private Boolean inundationHistory;

	@NotNull
	private String zipCode;
	@NotNull
	private String address;
	@NotNull
	private String addressDetail;
	@NotNull
	private String accountNumber;
	@NotNull
	private String accountHolder;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@NotNull
	private LocalDateTime meetingSchedule;

	@NotNull
	private Map<String, List<String>> options;

	public boolean isOldCar(final LocalDate today) {
		return today.getYear() - this.year.getYear() >= 12;
	}

	public boolean isDistanceTooShort() {
		return this.distance <= 5_000;
	}

	public boolean isBusyCustomer(final LocalDate today) {
		int createdYear = this.meetingSchedule.getYear();
		int month = this.meetingSchedule.getMonthValue();
		int day = this.meetingSchedule.getDayOfMonth();

		LocalDate meetingDate = LocalDate.of(createdYear, month, day);
		LocalDate tomorrow = today.plusDays(1L);

		return Objects.equals(meetingDate, tomorrow);
	}

}
