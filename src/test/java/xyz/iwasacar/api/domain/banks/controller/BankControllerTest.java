package xyz.iwasacar.api.domain.banks.controller;

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
import xyz.iwasacar.api.domain.banks.dto.response.BankResponse;
import xyz.iwasacar.api.domain.banks.entity.Bank;
import xyz.iwasacar.api.domain.banks.service.BankService;
import xyz.iwasacar.api.dummy.Dummy;

@WebMvcTest(BankController.class)
class BankControllerTest {

	MockMvc mockMvc;

	@MockBean
	BankService bankService;

	@MockBean
	JwtTokenParser jwtTokenParser;

	@MockBean
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	BankController bankController;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders
			.standaloneSetup(bankController)
			.alwaysDo(print())
			.build();
	}

	@DisplayName("은행 전체 조회")
	@Test
	void testFindBanks() throws Exception {
		int times = 10; // 전체 개수라 가정
		List<BankResponse> bankResponses = new ArrayList<>();

		for (int i = 1; i <= times; i++) {
			Bank bank = Dummy.getBank();
			BankResponse bankResponse = BankResponse.from(bank);
			bankResponses.add(bankResponse);
		}

		given(bankService.findBanks()).willReturn(bankResponses);

		mockMvc.perform(
				get("/api/v1/banks")
					.characterEncoding(StandardCharsets.UTF_8)
			)
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.code", is(OK.value())))
			.andExpect(jsonPath("$.data", hasSize(10)));

		then(bankService).should(times(1)).findBanks();

	}

}
