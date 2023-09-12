package xyz.iwasacar.api.domain.banks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.banks.entity.Bank;
import xyz.iwasacar.api.domain.banks.exception.BankNotFoundException;

public interface BankRepository extends JpaRepository<Bank, Long> {

	default Bank getBy(final Long id) {
		return findById(id).orElseThrow(BankNotFoundException::new);
	}

}
