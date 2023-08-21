package xyz.iwasacar.api.domain.products.service;

import xyz.iwasacar.api.domain.products.dto.response.ProductDetailResponse;

public interface ProductService {

	ProductDetailResponse findProductDetail(Long id);

}
