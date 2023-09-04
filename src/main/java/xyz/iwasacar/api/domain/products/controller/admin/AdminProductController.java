package xyz.iwasacar.api.domain.products.controller.admin;

import static org.springframework.http.HttpStatus.*;
import static xyz.iwasacar.api.domain.products.service.ProductService.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.products.dto.response.ProductDetailResponse;
import xyz.iwasacar.api.domain.products.dto.response.ProductResponse;
import xyz.iwasacar.api.domain.products.service.ProductService;

@RestController
@RequestMapping("/api/v1/admin/products")
public class AdminProductController {

	private final ProductService productService;

	public AdminProductController(@Qualifier(ADMIN_PRODUCT_SERVICE) ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<CommonResponse<PageResponse<ProductResponse>>> findProductsForAdmin(
		@RequestParam(required = false, defaultValue = "1") final Integer page,
		@RequestParam(required = false, defaultValue = "10") final Integer size
	) {

		PageResponse<ProductResponse> products = productService.findProducts(page, size);

		return CommonResponse.success(OK, OK.value(), products);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<CommonResponse<ProductDetailResponse>> findProduct(@PathVariable final Long productId) {

		ProductDetailResponse productDetail = productService.findProductDetail(productId);

		return CommonResponse.success(OK, OK.value(), productDetail);
	}

	@PostMapping
	public ResponseEntity<CommonResponse<ProductDetailResponse>> addProduct() {

		return CommonResponse.success(OK, OK.value(), null);
	}

	@PutMapping("/{productId}")
	public ResponseEntity<CommonResponse<ProductDetailResponse>> updateProduct(
		@PathVariable final Long productId,
		@RequestPart MultipartFile performanceCheck,
		@RequestPart List<MultipartFile> images
	) {

		ProductDetailResponse productDetail = productService.updateProduct(productId, performanceCheck, images);

		return CommonResponse.success(OK, OK.value(), productDetail);
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<CommonResponse<ProductDetailResponse>> deleteProduct(@PathVariable final Long productId) {

		productService.deleteProduct(productId);

		return CommonResponse.success(NO_CONTENT, NO_CONTENT.value(), null);
	}

}
