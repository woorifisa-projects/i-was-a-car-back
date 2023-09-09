package xyz.iwasacar.api.domain.products.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.products.dto.response.AdminProductUpdateResponse;
import xyz.iwasacar.api.domain.products.dto.response.ProductDetailResponse;
import xyz.iwasacar.api.domain.products.dto.response.ProductResponse;
import xyz.iwasacar.api.domain.products.dto.response.ProductSaleDetailResponse;

public interface ProductService {

	String ADMIN_PRODUCT_SERVICE = "AdminProductService";

	ProductDetailResponse findProductDetail(Long id);

	List<ProductResponse> findProducts(Long category, String keyword, Long lastProductId);

	PageResponse<ProductResponse> findProducts(Integer page, Integer size);

	List<ProductResponse> findSpecificProducts(Long carType, Integer capital, Integer loan, Long lastProductId);

	ProductDetailResponse updateProduct(Long productId, MultipartFile performanceCheck, List<MultipartFile> images);

	void deleteProduct(Long productId);

	PageResponse<ProductResponse> findWaitingProducts(Integer page, Integer size);

	ProductSaleDetailResponse findProductHistory(Long productId);

	String addPerformanceCheck(Long productId, MultipartFile performanceCheck);

	List<String> addAdminImages(Long productId, List<MultipartFile> images);

	AdminProductUpdateResponse updatePriceAndLabel(Long productId, Integer price, Long labelId);

}
