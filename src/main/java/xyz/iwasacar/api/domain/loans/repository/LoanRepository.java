package xyz.iwasacar.api.domain.loans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.loans.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>, LoanRepositoryCustom {
}
