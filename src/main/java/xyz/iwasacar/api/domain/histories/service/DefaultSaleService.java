package xyz.iwasacar.api.domain.histories.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.histories.dto.request.SaleRequest;
import xyz.iwasacar.api.domain.histories.dto.response.SaleResponse;
import xyz.iwasacar.api.domain.histories.repository.SaleHistoryRepository;

@Service
@RequiredArgsConstructor
public class DefaultSaleService implements SaleService {

	private final SaleHistoryRepository saleHistoryRepository;

	@Override
	public SaleResponse saveSalesHistory(List<MultipartFile> carImages, SaleRequest saleRequest) {
		return null;
	}

}
