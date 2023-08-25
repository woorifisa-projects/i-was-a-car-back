package xyz.iwasacar.api.domain.products.controller;

import static java.nio.charset.StandardCharsets.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

import xyz.iwasacar.api.domain.products.dto.response.ProductDetailResponse;
import xyz.iwasacar.api.domain.products.entity.Product;
import xyz.iwasacar.api.domain.products.service.ProductService;
import xyz.iwasacar.api.dummy.Dummy;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

	MockMvc mockMvc;

	@MockBean
	ProductService productService;

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
	void testFindProducts() {

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
