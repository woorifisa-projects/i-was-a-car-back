package xyz.iwasacar.api.domain.documentitems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.documentitems.entity.DocumentItem;

public interface DocumentItemRepository extends JpaRepository<DocumentItem, Long>, DocumentItemRepositoryCustom {
}
