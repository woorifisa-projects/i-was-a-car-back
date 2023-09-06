package xyz.iwasacar.api.domain.histories.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.histories.dto.response.PurchaseHistoryDetailResponse;
import xyz.iwasacar.api.domain.histories.dto.response.PurchaseResponse;
import xyz.iwasacar.api.domain.histories.entity.PurchaseHistory;
import xyz.iwasacar.api.domain.histories.repository.PurchaseHistoryRepository;
import xyz.iwasacar.api.domain.members.exception.MemberNotFoundException;

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

	@Override
	public Long findMemberId(Long purchaseHistoryNo) {

		PurchaseHistory purchaseHistory = purchaseHistoryRepository.findById(purchaseHistoryNo).orElseThrow(
			MemberNotFoundException::new);
		;
		Long userNoByPurchaseNo = purchaseHistory.getMember().getId();
		return userNoByPurchaseNo;
	}

	@Override
	public PurchaseHistoryDetailResponse findPurchaseDetail(Long purchaseHistoryNo) {
		PurchaseHistoryDetailResponse purchaseHistoryDetailResponse = purchaseHistoryRepository.findPurchaseDetail(
			purchaseHistoryNo);
		return purchaseHistoryDetailResponse;
	}
}
