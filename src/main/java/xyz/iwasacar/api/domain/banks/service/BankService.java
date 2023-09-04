package xyz.iwasacar.api.domain.banks.service;

import java.util.List;

import xyz.iwasacar.api.domain.banks.dto.response.BankResponse;

public interface BankService {

	List<BankResponse> findBanks();

}
