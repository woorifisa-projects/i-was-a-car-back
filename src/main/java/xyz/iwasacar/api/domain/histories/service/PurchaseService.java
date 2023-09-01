package xyz.iwasacar.api.domain.histories.service;

import xyz.iwasacar.api.domain.histories.dto.request.PurchaseHistoryRequest;
import xyz.iwasacar.api.domain.histories.dto.response.PurchaseHistoryResponse;

public interface PurchaseService {

	PurchaseHistoryResponse savePurchaseHistory(PurchaseHistoryRequest phr);
}
