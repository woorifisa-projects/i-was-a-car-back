package xyz.iwasacar.api.domain.banks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.banks.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {
}
