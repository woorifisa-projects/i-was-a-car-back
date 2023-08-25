package xyz.iwasacar.api.domain.logs.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.logs.dto.LogResponse;
import xyz.iwasacar.api.domain.logs.repository.LogRepository;

@Service
@RequiredArgsConstructor
public class DefaultLogService implements LogService {

	private final LogRepository logRepository;

	@Override
	public PageResponse<LogResponse> retrieveLogs(final int page, final int size) {

		Page<LogResponse> logs = logRepository.findLogs(page, size);

		return PageResponse.of(logs.getContent(), page, logs.getTotalPages());
	}

}
