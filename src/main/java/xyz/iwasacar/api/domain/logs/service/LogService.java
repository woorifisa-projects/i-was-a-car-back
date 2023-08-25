package xyz.iwasacar.api.domain.logs.service;

import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.logs.dto.LogResponse;

public interface LogService {

	PageResponse<LogResponse> retrieveLogs(int page, int size);

}
