package xyz.iwasacar.api.domain.histories.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import xyz.iwasacar.api.domain.histories.dto.request.SaleRequest;
import xyz.iwasacar.api.domain.histories.dto.response.SaleResponse;

public interface SaleService {

	SaleResponse saveSalesHistory(List<MultipartFile> carImages, SaleRequest saleRequest);

}
