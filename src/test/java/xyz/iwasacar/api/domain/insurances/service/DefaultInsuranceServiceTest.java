package xyz.iwasacar.api.domain.insurances.service;

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

import xyz.iwasacar.api.domain.insurances.dto.response.InsuranceResponse;
import xyz.iwasacar.api.domain.insurances.entity.Insurance;
import xyz.iwasacar.api.domain.insurances.repository.InsuranceRepository;
import xyz.iwasacar.api.dummy.Dummy;

@ExtendWith(MockitoExtension.class)
class DefaultInsuranceServiceTest {
	@Mock
	InsuranceRepository insuranceRepository;

	@InjectMocks
	DefaultInsuranceService insuranceService;

	@DisplayName("사용 가능한 보험 전체 조회")
	@Test
	void testFindUsableInsurances() {
		int times = 10;
		List<Insurance> insuranceList = new ArrayList<>();

		for (int i = 0; i < times; i++) {
			Insurance insurance = Dummy.getInsurance();

			insuranceList.add(insurance);
		}

		given(insuranceRepository.findAll()).willReturn(insuranceList);

		List<InsuranceResponse> insuranceResponses = insuranceService.findUsableInsurances();

		assertThat(insuranceResponses).hasSize(times);

		then(insuranceRepository).should(times(1)).findAll();
	}

}
