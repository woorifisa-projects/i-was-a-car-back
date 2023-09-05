package xyz.iwasacar.api.domain.cartypes.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import xyz.iwasacar.api.domain.cartypes.dto.response.CarTypeResponse;
import xyz.iwasacar.api.domain.cartypes.entity.CarType;
import xyz.iwasacar.api.domain.cartypes.repository.CarTypeRepository;
import xyz.iwasacar.api.dummy.Dummy;

@ExtendWith(MockitoExtension.class)
class DefaultCarTypeServiceTest {
	@Mock
	CarTypeRepository carTypeRepository;

	@InjectMocks
	DefaultCarTypeService carTypeService;

	@DisplayName("차종 전체 조회")
	@Test
	void testFindCarTypes() {
		int times = 10;
		List<CarType> carTypeList = new ArrayList<>();

		for (int i = 0; i < times; i++) {
			CarType carType = Dummy.getCarTypeDummy();

			carTypeList.add(carType);
		}

		given(carTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))).willReturn(carTypeList);

		List<CarTypeResponse> carTypeResponses = carTypeService.findCarTypes();

		assertThat(carTypeResponses).hasSize(times);

		then(carTypeRepository).should(times(1)).findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

}
