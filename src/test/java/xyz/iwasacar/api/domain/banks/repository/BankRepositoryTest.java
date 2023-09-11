package xyz.iwasacar.api.domain.banks.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import xyz.iwasacar.api.config.QueryDslTestConfig;
import xyz.iwasacar.api.domain.banks.entity.Bank;
import xyz.iwasacar.api.dummy.Dummy;

@DataJpaTest
@Import(QueryDslTestConfig.class)
class BankRepositoryTest {
	@Autowired
	BankRepository bankRepository;

	@DisplayName("은행 전체 찾기")
	@Test
	void testFindCarTypes() {
		int times = 10;

		List<Bank> banks = new ArrayList<>();

		for (int i = 0; i < times; i++) {
			Bank bank = Dummy.getBank();

			banks.add(bank);
		}

		bankRepository.saveAll(banks);

		List<Bank> bankList = bankRepository.findAll();
		assertThat(bankList).hasSize(10);

	}

}
