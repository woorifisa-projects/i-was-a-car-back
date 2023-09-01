package xyz.iwasacar.api.domain.consenthistories.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.domain.consenthistories.dto.request.ConsentHistoryRequest;
import xyz.iwasacar.api.domain.consenthistories.dto.response.ConsentHistoryResponse;
import xyz.iwasacar.api.domain.consenthistories.service.ConsentHistoryService;

@RestController
@RequestMapping("/api/v1/consents")
@RequiredArgsConstructor
public class ConsentHistoryController {
	private final ConsentHistoryService consentHistoryService;

	@PostMapping
	public ResponseEntity<CommonResponse<List<ConsentHistoryResponse>>> saveConsentHistories(
		@RequestBody List<ConsentHistoryRequest> consentHistoryRequests) {
		List<ConsentHistoryResponse> saveDdocumentConsentHistories = consentHistoryService.saveConsentHistories(
			consentHistoryRequests);
		return CommonResponse.success(CREATED, CREATED.value(), saveDdocumentConsentHistories);

	}
}
