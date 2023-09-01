package xyz.iwasacar.api.domain.cartypes.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.cartypes.dto.response.CarTypeResponse;
import xyz.iwasacar.api.domain.cartypes.entity.CarType;
import xyz.iwasacar.api.domain.cartypes.repository.CarTypeRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DefaultCarTypeService implements CarTypeService {

	private final CarTypeRepository carTypeRepository;

	@Override
	public List<CarTypeResponse> findCarTypes() {
		List<CarType> carTypes = carTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

		List<CarTypeResponse> carTypeResponses = carTypes.stream()
			.map(CarTypeResponse::of)
			.collect(Collectors.toList());

		return carTypeResponses;
	}
}
