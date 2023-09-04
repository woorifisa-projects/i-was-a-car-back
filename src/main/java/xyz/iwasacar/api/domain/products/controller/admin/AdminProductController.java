package xyz.iwasacar.api.domain.products.controller.admin;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.domain.products.dto.response.ProductResponse;
import xyz.iwasacar.api.domain.products.service.ProductService;

@RestController
@RequestMapping("/api/v1/admin/products")
public class AdminProductController {

	private final ProductService productService;

	public AdminProductController(@Qualifier("AdminProductService") ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<CommonResponse<List<ProductResponse>>> findProductsForAdmin(
		@RequestParam(required = false) final Integer page,
		@RequestParam(required = false) final Integer size
	) {

		List<ProductResponse> products = productService.findProducts(page, size);
		return CommonResponse.success(OK, OK.value(), products);
	}

}
