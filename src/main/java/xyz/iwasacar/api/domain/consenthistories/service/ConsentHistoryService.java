package xyz.iwasacar.api.domain.consenthistories.service;

import java.util.List;

import xyz.iwasacar.api.domain.consenthistories.dto.request.ConsentHistoryRequest;
import xyz.iwasacar.api.domain.consenthistories.dto.response.ConsentHistoryResponse;

public interface ConsentHistoryService {
	
	List<ConsentHistoryResponse> saveConsentHistories(List<ConsentHistoryRequest> consentHistoryRequests);
	
}
