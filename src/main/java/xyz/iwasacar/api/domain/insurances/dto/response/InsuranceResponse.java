package xyz.iwasacar.api.domain.insurances.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.insurances.entity.Insurance;

@Builder
@RequiredArgsConstructor
@Getter
public class InsuranceResponse {

	private final Long id;
	private final String name;
	private final String company;
	private final Integer period;
	private final Integer monthlyPremium;

	public static InsuranceResponse of(final Insurance insurance) {
		return InsuranceResponse.builder()
			.id(insurance.getId())
			.name(insurance.getName())
			.company(insurance.getCompany())
			.period(insurance.getPeriod())
			.monthlyPremium(insurance.getMonthlyPremium())
			.build();
	}

}
