package xyz.iwasacar.api.domain.histories.controller;

import static java.util.stream.Collectors.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static xyz.iwasacar.api.dummy.RequestUtil.*;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.LongStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import xyz.iwasacar.api.common.argumentresolver.MemberClaimArgumentResolver;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenParser;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenProvider;
import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.common.interceptor.BearerAuthInterceptor;
import xyz.iwasacar.api.config.JwtTestConfig;
import xyz.iwasacar.api.domain.histories.dto.request.ProductCreateRequest;
import xyz.iwasacar.api.domain.histories.dto.response.CarInfoResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleHistoryDetailResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleHistoryResponse;
import xyz.iwasacar.api.domain.histories.dto.response.SaleResponse;
import xyz.iwasacar.api.domain.histories.service.SaleService;
import xyz.iwasacar.api.dummy.DtoDummy;

@WebMvcTest(SaleHistoryController.class)
@Import(JwtTestConfig.class)
class SaleHistoryControllerTest {

	MockMvc mockMvc;

	@MockBean
	SaleService saleService;

	@Autowired
	SaleHistoryController saleHistoryController;

	@Autowired
	ObjectMapper om;

	@Autowired
	JwtTokenParser jwtTokenParser;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Value("${jwt.test}")
	String testJwt;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders
			.standaloneSetup(saleHistoryController)
			.addInterceptors(new BearerAuthInterceptor(jwtTokenParser, jwtTokenProvider))
			.setCustomArgumentResolvers(new MemberClaimArgumentResolver())
			.alwaysDo(print())
			.build();
	}

	@DisplayName("소유자와 차량번호로 차량 조회")
	@Test
	void testFindCarInfoByCarNumber() throws Exception {
		String name = "황동민";
		String carNumber = "123가1234";
		CarInfoResponse carInfoResponse = DtoDummy.carInfoResponse();

		given(saleService.findCarInfoByCarNumber(name, carNumber)).willReturn(carInfoResponse);

		mockMvc.perform(get("/api/v1/sales/car-info")
				.param("name", name)
				.param("carNumber", carNumber)
				.cookie(getCookie(testJwt, jwtTokenProvider.getRefreshTokenExpireTimeMils()))
			)
			.andExpect(status().isOk());

		then(saleService).should(times(1)).findCarInfoByCarNumber(name, carNumber);
	}

	@DisplayName("상품 판매")
	@Test
	void testSaveSalesHistory() throws Exception {

		SaleResponse saleResponse = DtoDummy.saleResponse();

		given(saleService.saveSalesHistory(any(ProductCreateRequest.class), anyList(), anyLong()))
			.willReturn(saleResponse);

		String image1Path = "src/test/resources/images/sonata1.jpeg";
		String image2Path = "src/test/resources/images/sonata2.jpeg";
		String image3Path = "src/test/resources/images/sonata3.jpeg";
		FileInputStream fis1 = new FileInputStream(image1Path);
		FileInputStream fis2 = new FileInputStream(image2Path);
		FileInputStream fis3 = new FileInputStream(image3Path);

		ProductCreateRequest request = DtoDummy.productCreateRequest();
		String requestBody = om.writeValueAsString(request);

		MockMultipartFile image1 = new MockMultipartFile("carImages", "sonata1.jpeg", "image/jpeg", fis1);
		MockMultipartFile image2 = new MockMultipartFile("carImages", "sonata2.jpeg", "image/jpeg", fis2);
		MockMultipartFile image3 = new MockMultipartFile("carImages", "sonata3.jpeg", "image/jpeg", fis3);
		MockMultipartFile dto = new MockMultipartFile("productCreateRequest", "", APPLICATION_JSON_VALUE,
			requestBody.getBytes(
				StandardCharsets.UTF_8));

		mockMvc.perform(
				multipart("/api/v1/sales")
					.file(image1)
					.file(image2)
					.file(image3)
					.file(dto)
					.cookie(getCookie(testJwt, jwtTokenProvider.getRefreshTokenExpireTimeMils()))
					// .part(new MockPart("productCreateRequest", requestBody.getBytes(StandardCharsets.UTF_8)))
					.contentType(APPLICATION_JSON_VALUE)
					.accept(APPLICATION_JSON))
			.andExpect(status().isCreated());

		then(saleService).should(times(1)).saveSalesHistory(any(ProductCreateRequest.class), anyList(), anyLong());
	}

	@DisplayName("판매 정보 목록 조회")
	@Test
	void testFindSaleHistories() throws Exception {
		Long memberId = 1L;
		int page = 1;
		int size = 8;

		List<SaleHistoryResponse> responses = LongStream.range(1, 9)
			.mapToObj(DtoDummy::saleHistoryResponse)
			.collect(toList());

		PageResponse<SaleHistoryResponse> pages = PageResponse.of(responses, page, 10);

		given(saleService.findSaleHistoriesByMember(memberId, page, size)).willReturn(pages);

		mockMvc.perform(
				get("/api/v1/sales/{memberId}/sale-histories", memberId)
					.cookie(getCookie(testJwt, jwtTokenProvider.getRefreshTokenExpireTimeMils()))
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.items.size()", is(size)))
			.andExpect(jsonPath("$.data.page", is(page)))
			.andExpect(jsonPath("$.data.totalPage", is(10)));

		then(saleService).should(times(1)).findSaleHistoriesByMember(memberId, 1, 8);

	}

	@DisplayName("판매정보 상세 조회")
	@Test
	void testFindSaleHistoryDetail() throws Exception {
		Long memberId = 1L;
		Long saleHistoryId = 1L;

		SaleHistoryDetailResponse response = DtoDummy.saleHistoryDetailResponse();

		given(saleService.findSaleHistoryDetail(saleHistoryId)).willReturn(response);

		mockMvc.perform(
				get("/api/v1/sales/{memberId}/sale-histories/{saleHistoryId}", memberId, saleHistoryId)
					.cookie(getCookie(testJwt, jwtTokenProvider.getRefreshTokenExpireTimeMils()))
			)
			.andExpect(status().isOk());

		then(saleService).should(times(1)).findSaleHistoryDetail(saleHistoryId);
	}

	@Test
	void forSwagger() throws Exception {
		mockMvc.perform(
				get("/api/v1/sales/api-docs")
					.cookie(getCookie(testJwt, jwtTokenProvider.getRefreshTokenExpireTimeMils())))
			.andExpect(status().is4xxClientError());
	}

}
