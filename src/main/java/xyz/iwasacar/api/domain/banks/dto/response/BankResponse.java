package xyz.iwasacar.api.domain.banks.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.banks.entity.Bank;

@RequiredArgsConstructor
@Getter
public class BankResponse {

	private final Long id;
	private final String name;

	public static BankResponse from(Bank bank) {
		return new BankResponse(bank.getId(), bank.getName());
	}

}
