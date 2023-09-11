package xyz.iwasacar.api.domain.insurances.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import xyz.iwasacar.api.config.QueryDslTestConfig;
import xyz.iwasacar.api.domain.insurances.entity.Insurance;
import xyz.iwasacar.api.dummy.Dummy;

@DataJpaTest
@Import(QueryDslTestConfig.class)
class InsuranceRepositoryTest {
	@Autowired
	InsuranceRepository insuranceRepository;

	@DisplayName("사용 가능한 보험 전체 찾기")
	@Test
	void testFindUsableInsurances() {
		int times = 10;

		List<Insurance> insurances = new ArrayList<>();

		for (int i = 0; i < times; i++) {
			Insurance insurance = Dummy.getInsurance();

			insurances.add(insurance);
		}

		insuranceRepository.saveAll(insurances);

		List<Insurance> insuranceList = insuranceRepository.findAll();
		assertThat(insuranceList).hasSize(10);

	}

}
