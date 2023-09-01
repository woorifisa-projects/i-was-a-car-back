package xyz.iwasacar.api.domain.documentitems.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.documentitems.dto.response.DocumentItemResponse;
import xyz.iwasacar.api.domain.documentitems.entity.DocumentItem;
import xyz.iwasacar.api.domain.documentitems.repository.DocumentItemRepository;

@Service
@RequiredArgsConstructor
public class DefaultDocumentItemService implements DocumentItemService {
	final private DocumentItemRepository documentItemRepository;

	@Override
	public List<DocumentItemResponse> findDocumentItems(Long documentId) {

		List<DocumentItem> documentItems = documentItemRepository.findByDocumentId(documentId);

		List<DocumentItemResponse> documentItemResponses = documentItems.stream()
			.map(DocumentItemResponse::of)
			.collect(Collectors.toList());

		return documentItemResponses;
	}
}
