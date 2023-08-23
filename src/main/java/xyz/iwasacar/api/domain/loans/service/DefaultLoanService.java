package xyz.iwasacar.api.domain.loans.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.loans.repository.LoanRepository;

@Service
@RequiredArgsConstructor
public class DefaultLoanService implements LoanService {

	private final LoanRepository loanRepository;

}
