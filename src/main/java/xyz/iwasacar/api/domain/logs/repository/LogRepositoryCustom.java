package xyz.iwasacar.api.domain.logs.repository;

import org.springframework.data.domain.Page;

import xyz.iwasacar.api.domain.logs.dto.LogResponse;

public interface LogRepositoryCustom {

	Page<LogResponse> findLogs(int page, int size);

}
