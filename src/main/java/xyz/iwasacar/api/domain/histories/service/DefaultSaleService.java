package xyz.iwasacar.api.domain.histories.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.component.AwsS3Uploader;
import xyz.iwasacar.api.domain.histories.dto.request.SaleRequest;
import xyz.iwasacar.api.domain.histories.dto.response.SaleResponse;
import xyz.iwasacar.api.domain.histories.repository.SaleHistoryRepository;

@Service
@RequiredArgsConstructor
public class DefaultSaleService implements SaleService {

	private static final String DIR_NAME = "images";

	private final SaleHistoryRepository saleHistoryRepository;
	private final AwsS3Uploader uploader;

	@Override
	public SaleResponse saveSalesHistory(List<MultipartFile> carImages, SaleRequest saleRequest) {

		String upload = uploader.upload(carImages.get(0), DIR_NAME);

		return new SaleResponse();
	}

}
