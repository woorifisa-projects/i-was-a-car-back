package xyz.iwasacar.api.domain.documentitems.service;

import static java.util.stream.Collectors.*;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.consenthistories.entity.DocumentConsentHistory;
import xyz.iwasacar.api.domain.documentitems.dto.request.DocumentConsentRequest;
import xyz.iwasacar.api.domain.documentitems.dto.response.DocumentItemResponse;
import xyz.iwasacar.api.domain.documentitems.entity.DocumentItem;
import xyz.iwasacar.api.domain.documentitems.repository.DocumentConsentHistoryRepository;
import xyz.iwasacar.api.domain.documentitems.repository.DocumentItemRepository;
import xyz.iwasacar.api.domain.documents.repository.DocumentRepository;
import xyz.iwasacar.api.domain.members.entity.Member;
import xyz.iwasacar.api.domain.members.repository.MemberRepository;
import xyz.iwasacar.api.domain.products.entity.Product;
import xyz.iwasacar.api.domain.products.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class DefaultDocumentItemService implements DocumentItemService {

	private final MemberRepository memberRepository;
	private final ProductRepository productRepository;
	private final DocumentRepository documentRepository;
	private final DocumentItemRepository documentItemRepository;
	private final DocumentConsentHistoryRepository documentConsentHistoryRepository;

	@Override
	public List<DocumentItemResponse> findDocumentItems(final Long documentId) {

		List<DocumentItem> documentItems = documentItemRepository.findByDocumentId(documentId);

		return documentItems.stream()
			.map(DocumentItemResponse::of)
			.collect(toList());
	}

	@Override
	public void consent(final Long memberId, final DocumentConsentRequest documentConsentRequest) {
		List<DocumentItem> items = documentItemRepository.findByDocumentId(documentConsentRequest.getDocumentId());
		List<DocumentItem> findItems = documentItemRepository.findAllInId(documentConsentRequest.getDocumentItemId());

		if (items.size() != findItems.size()) {
			throw new IllegalArgumentException();
		}

		Member member = memberRepository.getBy(memberId);
		List<DocumentConsentHistory> list;

		if (documentConsentRequest.getProductId() == null) {
			list = findItems.stream()
				.map(i -> DocumentConsentHistory.signupConsent(member, i, true))
				.collect(toList());
		} else {
			Product product = productRepository.getBy(documentConsentRequest.getProductId());
			list = findItems.stream()
				.map(i -> DocumentConsentHistory.contractConsent(member, i, product, true))
				.collect(toList());
		}

		documentConsentHistoryRepository.saveAll(list);
	}

}
