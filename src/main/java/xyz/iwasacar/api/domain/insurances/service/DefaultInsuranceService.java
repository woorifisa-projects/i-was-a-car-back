package xyz.iwasacar.api.domain.insurances.service;

import static java.util.stream.Collectors.*;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.insurances.dto.response.InsuranceResponse;
import xyz.iwasacar.api.domain.insurances.repository.InsuranceRepository;

@Service
@RequiredArgsConstructor
public class DefaultInsuranceService implements InsuranceService {

	private final InsuranceRepository insuranceRepository;

	public List<InsuranceResponse> findUsableInsurances() {
		return insuranceRepository.findAll()
			.stream()
			.map(InsuranceResponse::of)
			.collect(toList());
	}

}
