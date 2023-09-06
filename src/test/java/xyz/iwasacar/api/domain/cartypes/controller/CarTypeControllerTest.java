package xyz.iwasacar.api.domain.cartypes.controller;

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
import xyz.iwasacar.api.domain.cartypes.dto.response.CarTypeResponse;
import xyz.iwasacar.api.domain.cartypes.entity.CarType;
import xyz.iwasacar.api.domain.cartypes.service.CarTypeService;
import xyz.iwasacar.api.dummy.Dummy;

@WebMvcTest(CarTypeController.class)
class CarTypeControllerTest {

	MockMvc mockMvc;

	@MockBean
	CarTypeService carTypeService;

	@MockBean
	JwtTokenParser jwtTokenParser;

	@MockBean
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	CarTypeController carTypeController;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders
			.standaloneSetup(carTypeController)
			.alwaysDo(print())
			.build();
	}

	@DisplayName("차종 전체 조회")
	@Test
	void testFindCarTypes() throws Exception {
		int times = 10; // 전체 개수라 가정
		List<CarTypeResponse> carTypeResponses = new ArrayList<>();

		for (int i = 1; i <= times; i++) {
			CarType carType = Dummy.getCarTypeDummy();

			CarTypeResponse carTypeResponse = CarTypeResponse.of(carType);
			carTypeResponses.add(carTypeResponse);
		}

		given(carTypeService.findCarTypes()).willReturn(carTypeResponses);

		mockMvc.perform(
				get("/api/v1/car-types")
					.characterEncoding(StandardCharsets.UTF_8)
			)
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.code", is(OK.value())))
			.andExpect(jsonPath("$.data", hasSize(10)));

		then(carTypeService).should(times(1)).findCarTypes();

	}
}
