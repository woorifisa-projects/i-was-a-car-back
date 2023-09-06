package xyz.iwasacar.api.domain.products.controller;

import static java.nio.charset.StandardCharsets.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import xyz.iwasacar.api.common.auth.jwt.JwtTokenParser;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenProvider;
import xyz.iwasacar.api.domain.products.dto.response.ProductDetailResponse;
import xyz.iwasacar.api.domain.products.dto.response.ProductResponse;
import xyz.iwasacar.api.domain.products.entity.Product;
import xyz.iwasacar.api.domain.products.service.ProductService;
import xyz.iwasacar.api.domain.resources.entity.ProductImage;
import xyz.iwasacar.api.dummy.Dummy;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

	MockMvc mockMvc;

	@MockBean
	ProductService productService;

	@MockBean
	JwtTokenParser jwtTokenParser;

	@MockBean
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	ProductController productController;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders
			.standaloneSetup(productController)
			.alwaysDo(print())
			.build();
	}

	@DisplayName("상품 목록 조회")
	@Test
	void testFindProducts() throws Exception {
		int times = 10;
		//TODO: null로 해도 되나
		Long carType = null;
		String keyword = null;
		Long lastProductId = 11L;
		List<ProductResponse> productResponses = new ArrayList<>();

		for (int i = 0; i < times; i++) {
			Product product = Dummy.getProduct(Dummy.getCarTypeDummy(), Dummy.getColor(), Dummy.getLabel(),
				Dummy.getBrand(), Dummy.getPerformanceCheck());

			ProductImage productImage = Dummy.getProductImage(product, Dummy.getResource(), Dummy.getAdminRole());

			ProductResponse response = ProductResponse.of(productImage);
			productResponses.add(response);
		}

		given(productService.findProducts(carType, keyword, lastProductId)).willReturn(productResponses);

		mockMvc.perform(
				get("/api/v1/products")
					.characterEncoding(UTF_8)
					.param("lastProductId", lastProductId.toString())
			)
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON))
			.andExpect(jsonPath("$.code", is(OK.value())))
			.andExpect(jsonPath("$.data", hasSize(10)));

		then(productService).should(times(1)).findProducts(carType, keyword, lastProductId);

	}

	@DisplayName("상품 상세 조회")
	@Test
	void testFindProductDetail() throws Exception {
		Long productId = 1L;
		Product product = Dummy.getProduct(Dummy.getCarTypeDummy(), Dummy.getColor(), Dummy.getLabel(),
			Dummy.getBrand(), Dummy.getPerformanceCheck());
		ReflectionTestUtils.setField(product, "id", productId);

		ProductDetailResponse response = ProductDetailResponse.of(product, List.of(), Map.of());

		given(productService.findProductDetail(productId)).willReturn(response);

		mockMvc.perform(
				get("/api/v1/products/{productId}", productId)
					.characterEncoding(UTF_8)
			)
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON))
			.andExpect(jsonPath("$.code", is(OK.value())))
			.andExpect(jsonPath("$.data.id", is(productId), Long.class));

		then(productService).should(times(1)).findProductDetail(productId);

	}

}
