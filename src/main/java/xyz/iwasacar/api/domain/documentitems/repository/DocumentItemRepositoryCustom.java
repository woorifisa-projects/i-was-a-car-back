package xyz.iwasacar.api.domain.documentitems.repository;

import java.util.List;

import xyz.iwasacar.api.domain.documentitems.entity.DocumentItem;

public interface DocumentItemRepositoryCustom {

	List<DocumentItem> findByDocumentId(Long documentId);

	List<DocumentItem> findAllInId(List<Long> id);

}
