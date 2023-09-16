package xyz.iwasacar.api.domain.logs.controller;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.logs.dto.LogResponse;
import xyz.iwasacar.api.domain.logs.service.LogService;

@RestController
@RequestMapping("/api/v1/admin/logs")
@RequiredArgsConstructor
public class LogController {

	private final LogService logService;

	@GetMapping
	public ResponseEntity<CommonResponse<PageResponse<LogResponse>>> retrieveLogs(
		@RequestParam(required = false, defaultValue = "1") final Integer page,
		@RequestParam(required = false, defaultValue = "8") final Integer size
	) {
		PageResponse<LogResponse> logs = logService.retrieveLogs(page, size);
		return CommonResponse.success(OK, OK.value(), logs);
	}

}
