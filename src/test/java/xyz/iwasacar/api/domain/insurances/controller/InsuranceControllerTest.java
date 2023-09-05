package xyz.iwasacar.api.domain.insurances.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import xyz.iwasacar.api.common.auth.jwt.JwtTokenParser;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenProvider;
import xyz.iwasacar.api.domain.insurances.dto.response.InsuranceResponse;
import xyz.iwasacar.api.domain.insurances.entity.Insurance;
import xyz.iwasacar.api.domain.insurances.service.InsuranceService;
import xyz.iwasacar.api.dummy.Dummy;

@WebMvcTest(InsuranceController.class)
class InsuranceControllerTest {

	MockMvc mockMvc;

	@MockBean
	InsuranceService insuranceService;

	@MockBean
	JwtTokenParser jwtTokenParser;

	@MockBean
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	InsuranceController insuranceController;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders
			.standaloneSetup(insuranceController)
			.alwaysDo(print())
			.build();
	}

	@DisplayName("사용가능한 보험 전체 조회")
	@Test
	void testFindUsableInsurances() throws Exception {
		int times = 10; // 전체 개수라 가정
		List<InsuranceResponse> insuranceResponses = new ArrayList<>();

		for (int i = 1; i <= times; i++) {
			Insurance insurance = Dummy.getInsurance();

			InsuranceResponse insuranceResponse = InsuranceResponse.of(insurance);
			insuranceResponses.add(insuranceResponse);
		}

		given(insuranceService.findUsableInsurances()).willReturn(insuranceResponses);

		mockMvc.perform(
				get("/api/v1/insurances")
					.characterEncoding(StandardCharsets.UTF_8)
			)
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.code", is(OK.value())))
			.andExpect(jsonPath("$.data", hasSize(10)));

		then(insuranceService).should(times(1)).findUsableInsurances();

	}

}
