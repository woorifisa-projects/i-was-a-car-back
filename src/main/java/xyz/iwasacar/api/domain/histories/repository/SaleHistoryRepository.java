package xyz.iwasacar.api.domain.histories.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.histories.entity.SaleHistory;

public interface SaleHistoryRepository extends JpaRepository<SaleHistory, Long>, SaleHistoryRepositoryCustom {

}
