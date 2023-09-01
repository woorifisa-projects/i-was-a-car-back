package xyz.iwasacar.api.domain.caroptions.entity;

import static java.util.stream.Collectors.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "car_options")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CarOption {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_option_no")
	private Long id;

	@Column(name = "type", nullable = false, length = 20)
	private String type;

	@Column(name = "name", nullable = false)
	private String name;

	public static Map<String, List<String>> convertCarOption(List<CarOption> carOptions) {
		Map<String, List<CarOption>> options = carOptions.stream()
			.collect(groupingBy(CarOption::getType));

		Map<String, List<String>> map = new HashMap<>();
		options.forEach(
			(key, value) -> map.put(key, value
				.stream()
				.map(CarOption::getName)
				.collect(toList()))
		);
		return map;
	}

}
