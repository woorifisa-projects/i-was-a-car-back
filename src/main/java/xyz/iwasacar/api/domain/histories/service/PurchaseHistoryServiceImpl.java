package xyz.iwasacar.api.domain.histories.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.histories.dto.PurchaseResponse;
import xyz.iwasacar.api.domain.histories.repository.PurchaseHistoryRepository;

@Service
@RequiredArgsConstructor
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService {

	private final PurchaseHistoryRepository purchaseHistoryRepository;

	@Override
	public PageResponse<PurchaseResponse> findAllPurchase(Long cookieById, Integer page, Integer size) {
		//
		Page<PurchaseResponse> purchases = purchaseHistoryRepository.findAllPurchase(cookieById, page, size);

		return new PageResponse<>(purchases.getContent(), page, purchases.getTotalPages());

	}
}
