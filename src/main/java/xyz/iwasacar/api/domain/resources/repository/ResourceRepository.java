package xyz.iwasacar.api.domain.resources.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.resources.entity.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long>, ResourceRepositoryCustom {
}
