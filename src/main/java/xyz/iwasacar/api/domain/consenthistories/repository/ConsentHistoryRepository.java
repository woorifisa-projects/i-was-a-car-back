package xyz.iwasacar.api.domain.consenthistories.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.consenthistories.entity.DocumentConsentHistory;

public interface ConsentHistoryRepository extends JpaRepository<DocumentConsentHistory, Long> {

}
