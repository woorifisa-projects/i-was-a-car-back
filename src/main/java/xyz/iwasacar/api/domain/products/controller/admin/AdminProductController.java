package xyz.iwasacar.api.domain.products.controller.admin;

import static org.springframework.http.HttpStatus.*;
import static xyz.iwasacar.api.domain.products.service.ProductService.*;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import xyz.iwasacar.api.common.annotation.Login;
import xyz.iwasacar.api.common.auth.jwt.MemberClaim;
import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.histories.dto.request.ProductCreateRequest;
import xyz.iwasacar.api.domain.histories.dto.response.SaleResponse;
import xyz.iwasacar.api.domain.histories.service.SaleService;
import xyz.iwasacar.api.domain.products.dto.reqeust.AdminProductUpdateRequest;
import xyz.iwasacar.api.domain.products.dto.response.AdminProductUpdateResponse;
import xyz.iwasacar.api.domain.products.dto.response.ProductDetailResponse;
import xyz.iwasacar.api.domain.products.dto.response.ProductResponse;
import xyz.iwasacar.api.domain.products.dto.response.ProductSaleDetailResponse;
import xyz.iwasacar.api.domain.products.service.ProductService;

@RestController
@RequestMapping("/api/v1/admin/products")
public class AdminProductController {

	private final ProductService productService;
	private final SaleService saleService;

	public AdminProductController(
		@Qualifier(ADMIN_PRODUCT_SERVICE) ProductService productService,
		SaleService saleService) {

		this.productService = productService;
		this.saleService = saleService;
	}

	@GetMapping
	public ResponseEntity<CommonResponse<PageResponse<ProductResponse>>> findProductsForAdmin(
		@RequestParam(required = false, defaultValue = "1") final Integer page,
		@RequestParam(required = false, defaultValue = "8") final Integer size
	) {

		PageResponse<ProductResponse> products = productService.findProducts(page, size);

		return CommonResponse.success(OK, OK.value(), products);
	}

	@GetMapping("/waiting")
	public ResponseEntity<CommonResponse<PageResponse<ProductResponse>>> findWaitingProducts(
		@RequestParam(required = false, defaultValue = "1") final Integer page,
		@RequestParam(required = false, defaultValue = "8") final Integer size
	) {
		PageResponse<ProductResponse> waitingProducts = productService.findWaitingProducts(page, size);

		return CommonResponse.success(OK, OK.value(), waitingProducts);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<CommonResponse<ProductDetailResponse>> findProduct(@PathVariable final Long productId) {

		ProductDetailResponse productDetail = productService.findProductDetail(productId);

		return CommonResponse.success(OK, OK.value(), productDetail);
	}

	@PostMapping(
		consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommonResponse<SaleResponse>> createProduct(
		@RequestPart ProductCreateRequest productCreateRequest,
		@RequestPart List<MultipartFile> carImages,
		@Login MemberClaim memberClaim
	) {

		SaleResponse saleResponse = saleService
			.saveSalesHistory(productCreateRequest, carImages, memberClaim.getMemberId());

		return CommonResponse.success(CREATED, CREATED.value(), saleResponse);
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

	@GetMapping("/history/{productId}")
	public ResponseEntity<CommonResponse<ProductSaleDetailResponse>> findProductHistory(
		@PathVariable final Long productId) {
		ProductSaleDetailResponse productSaleDetailResponse = productService.findProductHistory(productId);

		return CommonResponse.success(OK, OK.value(), productSaleDetailResponse);
	}

	@PatchMapping("/{productId}/performance-check")
	public ResponseEntity<CommonResponse<String>> addPerformanceCheck(
		@PathVariable final Long productId,
		@RequestPart final MultipartFile performanceCheck
	) {
		String performanceCheckUrl = productService.addPerformanceCheck(productId, performanceCheck);

		return CommonResponse.success(OK, OK.value(), performanceCheckUrl);
	}

	@PatchMapping("/{productId}/images")
	public ResponseEntity<CommonResponse<List<String>>> addAdminImages(
		@PathVariable final Long productId,
		@RequestPart final List<MultipartFile> images
	) {
		List<String> imageUrls = productService.addAdminImages(productId, images);

		return CommonResponse.success(OK, OK.value(), imageUrls);
	}

	@PatchMapping("/{productId}")
	public ResponseEntity<CommonResponse<AdminProductUpdateResponse>> updatePriceAndLabel(
		@PathVariable Long productId, @RequestBody @Valid AdminProductUpdateRequest request) {

		AdminProductUpdateResponse adminProductUpdateResponse = productService.updatePriceAndLabel(productId,
			request.getPrice(), request.getLabelId());

		return CommonResponse.success(OK, OK.value(), adminProductUpdateResponse);
	}

}
