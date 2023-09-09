package xyz.iwasacar.api.domain.consenthistories.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.consenthistories.dto.request.ConsentHistoryRequest;
import xyz.iwasacar.api.domain.consenthistories.dto.response.ConsentHistoryResponse;
import xyz.iwasacar.api.domain.consenthistories.entity.DocumentConsentHistory;
import xyz.iwasacar.api.domain.consenthistories.repository.ConsentHistoryRepository;
import xyz.iwasacar.api.domain.documentitems.entity.DocumentItem;
import xyz.iwasacar.api.domain.documentitems.exception.DocumentItemNotFound;
import xyz.iwasacar.api.domain.documentitems.repository.DocumentItemRepository;
import xyz.iwasacar.api.domain.members.entity.Member;
import xyz.iwasacar.api.domain.members.exception.MemberNotFoundException;
import xyz.iwasacar.api.domain.members.repository.MemberRepository;
import xyz.iwasacar.api.domain.products.entity.Product;
import xyz.iwasacar.api.domain.products.exception.ProductNotFound;
import xyz.iwasacar.api.domain.products.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class DefaultConsentHistoryService implements ConsentHistoryService {
	private final ConsentHistoryRepository consentHistoryRepository;
	private final MemberRepository memberRepository;
	private final ProductRepository productRepository;
	private final DocumentItemRepository documentItemRepository;

	@Transactional
	@Override
	public List<ConsentHistoryResponse> saveConsentHistories(List<ConsentHistoryRequest> consentHistoryRequests) {

		List<DocumentConsentHistory> documentConsentHistories = consentHistoryRequests.stream()
			.map(this::toEntity)
			.collect(
				Collectors.toList());
		List<DocumentConsentHistory> savedDocumentConsentHistories = consentHistoryRepository.saveAll(
			documentConsentHistories);
		List<ConsentHistoryResponse> consentHistoryResponses = savedDocumentConsentHistories.stream()
			.map(ConsentHistoryResponse::of)
			.collect(
				Collectors.toList());

		return consentHistoryResponses;
	}

	private DocumentConsentHistory toEntity(ConsentHistoryRequest consentHistoryRequest) {
		Member member = memberRepository.findById(consentHistoryRequest.getMemberId())
			.orElseThrow(MemberNotFoundException::new);
		DocumentItem documentItem = documentItemRepository.findById(consentHistoryRequest.getDocumentItemId())
			.orElseThrow(DocumentItemNotFound::new);
		if (consentHistoryRequest.getProductId() != null) {
			Product product = productRepository.findById(consentHistoryRequest.getProductId()).orElseThrow(
				ProductNotFound::new);
			return DocumentConsentHistory.contractConsent(member, documentItem, product,
				consentHistoryRequest.getConsent());
		}

		return DocumentConsentHistory.signupConsent(member, documentItem, consentHistoryRequest.getConsent());

	}
}
