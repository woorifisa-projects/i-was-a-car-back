package xyz.iwasacar.api.domain.products.service;

import java.util.List;

import xyz.iwasacar.api.domain.products.dto.response.ProductDetailResponse;
import xyz.iwasacar.api.domain.products.dto.response.ProductsResponse;

public interface ProductService {

	ProductDetailResponse findProductDetail(Long id);

	List<ProductsResponse> findProducts(Long id);

}
