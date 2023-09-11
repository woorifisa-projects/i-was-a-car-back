package xyz.iwasacar.api.domain.cartypes.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import xyz.iwasacar.api.config.QueryDslTestConfig;
import xyz.iwasacar.api.domain.cartypes.entity.CarType;
import xyz.iwasacar.api.dummy.Dummy;

@DataJpaTest
@Import(QueryDslTestConfig.class)
class CarTypeRepositoryTest {
	@Autowired
	CarTypeRepository carTypeRepository;

	@DisplayName("차종 전체 찾기")
	@Test
	void testFindCarTypes() {
		int times = 10;

		List<CarType> carTypes = new ArrayList<>();

		for (int i = 0; i < times; i++) {
			CarType carType = Dummy.getCarTypeDummy();

			carTypes.add(carType);
		}

		carTypeRepository.saveAll(carTypes);

		List<CarType> carTypeList = carTypeRepository.findAll();
		assertThat(carTypeList).hasSize(10);

	}

}
