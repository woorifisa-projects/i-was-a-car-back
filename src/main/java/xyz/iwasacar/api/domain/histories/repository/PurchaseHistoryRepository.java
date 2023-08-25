package xyz.iwasacar.api.domain.histories.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.histories.entity.PurchaseHistory;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long>, PurchaseHistoryCustom {
}
