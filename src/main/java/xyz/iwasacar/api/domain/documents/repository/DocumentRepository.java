package xyz.iwasacar.api.domain.documents.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.documentitems.exception.DocumentItemNotFound;
import xyz.iwasacar.api.domain.documents.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

	default Document getBy(final Long id) {
		return findById(id).orElseThrow(DocumentItemNotFound::new);
	}

}
