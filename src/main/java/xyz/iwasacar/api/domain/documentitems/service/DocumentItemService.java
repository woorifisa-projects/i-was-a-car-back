package xyz.iwasacar.api.domain.documentitems.service;

import java.util.List;

import xyz.iwasacar.api.domain.documentitems.dto.response.DocumentItemResponse;

public interface DocumentItemService {
	List<DocumentItemResponse> findDocumentItems(Long documentId);
}