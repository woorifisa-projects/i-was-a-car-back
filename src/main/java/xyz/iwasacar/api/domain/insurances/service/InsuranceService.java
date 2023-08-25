package xyz.iwasacar.api.domain.insurances.service;

import java.util.List;

import xyz.iwasacar.api.domain.insurances.dto.response.InsuranceResponse;

public interface InsuranceService {

	List<InsuranceResponse> findUsableInsurances();

}
