package xyz.iwasacar.api.domain.logs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.logs.entity.Log;

public interface LogRepository extends JpaRepository<Log, Long>, LogRepositoryCustom {
}
