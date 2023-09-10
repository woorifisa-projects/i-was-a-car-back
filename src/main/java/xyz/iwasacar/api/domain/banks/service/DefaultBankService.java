package xyz.iwasacar.api.domain.banks.service;

import static java.util.stream.Collectors.*;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.banks.dto.response.BankResponse;
import xyz.iwasacar.api.domain.banks.repository.BankRepository;

@Service
@RequiredArgsConstructor
public class DefaultBankService implements BankService {

	private final BankRepository bankRepository;

	@Override
	public List<BankResponse> findBanks() {
		return bankRepository.findAll()
			.stream()
			.map(BankResponse::from)
			.collect(toList());
	}
}
