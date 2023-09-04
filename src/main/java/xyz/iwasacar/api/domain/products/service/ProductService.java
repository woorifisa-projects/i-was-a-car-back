package xyz.iwasacar.api.domain.products.service;

import java.util.List;

import xyz.iwasacar.api.domain.products.dto.response.ProductDetailResponse;
import xyz.iwasacar.api.domain.products.dto.response.ProductResponse;

public interface ProductService {

	ProductDetailResponse findProductDetail(Long id);

	List<ProductResponse> findProducts(Long id);

	List<ProductResponse> findProducts(int page, int size);

	List<ProductResponse> findSpecificProducts(Long carType, Integer capital, Integer loan, Long lastProductId);

}
