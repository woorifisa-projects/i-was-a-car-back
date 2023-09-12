package xyz.iwasacar.api.domain.histories.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import xyz.iwasacar.api.config.JwtTestConfig;
import xyz.iwasacar.api.domain.histories.dto.request.PurchaseHistoryRequest;
import xyz.iwasacar.api.domain.histories.dto.response.PurchaseHistoryResponse;
import xyz.iwasacar.api.domain.histories.service.PurchaseService;
import xyz.iwasacar.api.dummy.DtoDummy;

@WebMvcTest(HistoryController.class)
@Import(JwtTestConfig.class)
class HistoryControllerTest {

	MockMvc mockMvc;

	@Autowired
	ObjectMapper om;

	@Autowired
	HistoryController historyController;

	@MockBean
	PurchaseService purchaseService;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(historyController)
			.alwaysDo(print())
			.build();
	}

	@DisplayName("상품 구매하기")
	@Test
	void testSavePurchaseHistory() throws Exception {

		PurchaseHistoryResponse purchaseHistoryResponse = DtoDummy.purchaseHistoryResponse();

		Map<String, Object> map = new HashMap<>();
		map.put("memberId", 1L);
		map.put("productId", 1L);
		map.put("bankId", 1L);
		map.put("loanId", 1L);
		map.put("insuranceId", 1L);
		map.put("zipCode", "12345");
		map.put("address", "서울시");
		map.put("addressDetail", "1234");
		map.put("accountNumber", "12341234");
		map.put("accountHolder", "황동민");
		map.put("loanAmount", 1234);
		map.put("period", 60);
		map.put("deliverySchedule", "2023-09-11 12:30");

		String requestBody = om.writeValueAsString(map);

		given(purchaseService.savePurchaseHistory(any(PurchaseHistoryRequest.class)))
			.willReturn(purchaseHistoryResponse);

		mockMvc.perform(post("/api/v1/histories/purchase")
				.contentType(APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(requestBody))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.data.memberName", is(purchaseHistoryResponse.getMemberName())))
			.andExpect(jsonPath("$.data.productName", is(purchaseHistoryResponse.getProductName())));

		then(purchaseService).should(times(1)).savePurchaseHistory(any(PurchaseHistoryRequest.class));
	}

}