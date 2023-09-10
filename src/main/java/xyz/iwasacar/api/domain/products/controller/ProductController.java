package xyz.iwasacar.api.domain.products.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.domain.products.dto.response.ProductDetailResponse;
import xyz.iwasacar.api.domain.products.dto.response.ProductResponse;
import xyz.iwasacar.api.domain.products.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	/*
	 * @param category: Long (1:차종 , 2:제조사)
	 * @param keyword: String 사용자가 검색한 내용
	 */
	@GetMapping
	public ResponseEntity<CommonResponse<List<ProductResponse>>> findProducts(
		@RequestParam(required = false) final Long category, @RequestParam(required = false) final String keyword,
		@RequestParam(required = false) final Long lastProductId) {
		List<ProductResponse> products = productService.findProducts(category, keyword, lastProductId);

		return CommonResponse.success(OK, OK.value(), products);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<CommonResponse<ProductDetailResponse>> findProductDetail(
		@PathVariable final Long productId) {
		ProductDetailResponse productDetail = productService.findProductDetail(productId);

		return CommonResponse.success(OK, OK.value(), productDetail);
	}

	/*
	 * @param carType: String 차종
	 * @param capital: int 자본금
	 * @param loan: int 원하는 대출금
	 */
	@GetMapping("/recommendation")
	public ResponseEntity<CommonResponse<List<ProductResponse>>> findSpecificProducts(
		@RequestParam final Long carType, @RequestParam final Integer capital, @RequestParam final Integer loan,
		@RequestParam(required = false) final Long lastProductId) {
		List<ProductResponse> productDetail = productService.findSpecificProducts(carType, capital, loan,
			lastProductId);

		return CommonResponse.success(OK, OK.value(), productDetail);
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<CommonResponse<Void>> deleteProduct(@PathVariable final Long productId) {

		productService.deleteProduct(productId);

		return CommonResponse.success(NO_CONTENT, NO_CONTENT.value(), null);
	}

}
