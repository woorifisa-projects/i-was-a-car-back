package xyz.iwasacar.api.domain.documentitems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.consenthistories.entity.DocumentConsentHistory;

public interface DocumentConsentHistoryRepository extends JpaRepository<DocumentConsentHistory, Long> {
}
