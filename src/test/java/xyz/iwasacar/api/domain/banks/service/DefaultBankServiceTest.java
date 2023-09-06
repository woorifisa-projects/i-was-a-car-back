package xyz.iwasacar.api.domain.banks.service;

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

import xyz.iwasacar.api.domain.banks.dto.response.BankResponse;
import xyz.iwasacar.api.domain.banks.entity.Bank;
import xyz.iwasacar.api.domain.banks.repository.BankRepository;
import xyz.iwasacar.api.dummy.Dummy;

@ExtendWith(MockitoExtension.class)
class DefaultBankServiceTest {

	@Mock
	BankRepository bankRepository;

	@InjectMocks
	DefaultBankService bankService;

	@DisplayName("은행 전체 조회")
	@Test
	void findBanks() {
		int times = 10;
		List<Bank> bankList = new ArrayList<>();

		for (int i = 0; i < times; i++) {
			Bank bank = Dummy.getBank();

			bankList.add(bank);
		}

		given(bankRepository.findAll()).willReturn(bankList);

		List<BankResponse> bankResponses = bankService.findBanks();

		assertThat(bankResponses).hasSize(times);

		then(bankRepository).should(times(1)).findAll();
	}

}
