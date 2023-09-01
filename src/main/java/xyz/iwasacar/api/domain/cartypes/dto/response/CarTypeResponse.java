package xyz.iwasacar.api.domain.cartypes.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.cartypes.entity.CarType;

@RequiredArgsConstructor
@Getter
public class CarTypeResponse {
	private final Long Id;

	private final String name;

	public static CarTypeResponse of(CarType carType) {
		return new CarTypeResponse(carType.getId(), carType.getName());
	}
}
