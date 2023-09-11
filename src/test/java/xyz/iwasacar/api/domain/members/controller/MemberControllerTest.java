package xyz.iwasacar.api.domain.members.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static xyz.iwasacar.api.common.auth.jwt.JwtUtil.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import xyz.iwasacar.api.common.argumentresolver.MemberClaimArgumentResolver;
import xyz.iwasacar.api.common.auth.email.EmailSession;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenParser;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenProvider;
import xyz.iwasacar.api.common.interceptor.BearerAuthInterceptor;
import xyz.iwasacar.api.config.JwtTestConfig;
import xyz.iwasacar.api.domain.members.dto.response.MemberResponse;
import xyz.iwasacar.api.domain.members.service.MemberService;
import xyz.iwasacar.api.dummy.DtoDummy;
import xyz.iwasacar.api.dummy.RequestUtil;

@WebMvcTest(MemberController.class)
@Import(JwtTestConfig.class)
class MemberControllerTest {

	MockMvc mockMvc;

	@MockBean
	MemberService memberService;

	@MockBean
	EmailSession emailSession;

	@Autowired
	MemberController memberController;

	@Autowired
	JwtTokenParser jwtTokenParser;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Value("${jwt.test}")
	String testJwt;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders
			.standaloneSetup(memberController)
			.addInterceptors(new BearerAuthInterceptor(jwtTokenParser, jwtTokenProvider))
			.setCustomArgumentResolvers(new MemberClaimArgumentResolver())
			.alwaysDo(print())
			.build();
	}

	@DisplayName("로그인 검증")
	@Test
	void testAuthenticate() throws Exception {

		Long id = jwtTokenParser.getMemberClaims(testJwt).getMemberId();
		MockHttpSession session = spy(new MockHttpSession());
		MemberResponse memberResponse = DtoDummy.memberResponse(id);
		session.setAttribute(AUTH_INFO, memberResponse);

		mockMvc.perform(
				get("/api/v1/members/auth")
					.cookie(RequestUtil.getCookie(testJwt, jwtTokenProvider.getRefreshTokenExpireTimeMils()))
					.session(session)
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.id", is(id), Long.class));

		then(session).should(times(2)).getAttribute(AUTH_INFO);
	}

	@Test
	void signup() throws Exception {

	}

	@Test
	void login() throws Exception {
	}

	@Test
	void logout() throws Exception {
	}

	@Test
	void findMembers() throws Exception {
	}

	@Test
	void findMember() throws Exception {
	}

	@Test
	void updateMember() throws Exception {
	}

	@Test
	void deleteMember() throws Exception {
	}

	@Test
	void retrieveIdentification() throws Exception {
	}

}
