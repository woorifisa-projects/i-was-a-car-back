package xyz.iwasacar.api.domain.members.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static xyz.iwasacar.api.common.auth.jwt.JwtUtil.*;
import static xyz.iwasacar.api.dummy.RequestUtil.*;

import java.nio.charset.StandardCharsets;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import xyz.iwasacar.api.common.argumentresolver.MemberClaimArgumentResolver;
import xyz.iwasacar.api.common.auth.email.EmailSession;
import xyz.iwasacar.api.common.auth.jwt.JwtDto;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenParser;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenProvider;
import xyz.iwasacar.api.common.interceptor.BearerAuthInterceptor;
import xyz.iwasacar.api.config.JwtTestConfig;
import xyz.iwasacar.api.domain.members.dto.request.LoginRequest;
import xyz.iwasacar.api.domain.members.dto.request.SignupRequest;
import xyz.iwasacar.api.domain.members.dto.request.UpdateRequest;
import xyz.iwasacar.api.domain.members.dto.response.MemberJwtResponse;
import xyz.iwasacar.api.domain.members.dto.response.MemberResponse;
import xyz.iwasacar.api.domain.members.dto.response.MemberUpdateResponse;
import xyz.iwasacar.api.domain.members.service.MemberService;
import xyz.iwasacar.api.dummy.DtoDummy;

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

	@Autowired
	ObjectMapper om;

	@Value("${jwt.test}")
	String testJwt;

	@BeforeEach
	void setUp() {
		String[] path = {"/api/v1/members/auth", "/api/v1/members", "/api/v1/members/identification"};

		mockMvc = MockMvcBuilders
			.standaloneSetup(memberController)
			.addMappedInterceptors(path, new BearerAuthInterceptor(jwtTokenParser, jwtTokenProvider))
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
					.cookie(getCookie(testJwt, jwtTokenProvider.getRefreshTokenExpireTimeMils()))
					.session(session)
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.id", is(id), Long.class));

		then(session).should(times(2)).getAttribute(AUTH_INFO);
	}

	@DisplayName("회원가입")
	@Test
	void signup() throws Exception {

		JwtDto jwtDto = new JwtDto(testJwt, testJwt);
		MemberResponse response = DtoDummy.memberResponse(17L);
		MemberJwtResponse memberJwtResponse = new MemberJwtResponse(response, jwtDto);
		SignupRequest request = DtoDummy.signupRequest();
		String requestBody = om.writeValueAsString(request);

		given(emailSession.verifyEmailCode(request.getEmail(), request.getCode())).willReturn(true);
		given(memberService.signup(any(SignupRequest.class))).willReturn(memberJwtResponse);

		mockMvc.perform(
				post("/api/v1/members/signup")
					.contentType(APPLICATION_JSON)
					.characterEncoding(StandardCharsets.UTF_8)
					.content(requestBody))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.id", is(response.getId()), Long.class));

		then(memberService).should(times(1)).signup(any(SignupRequest.class));
	}

	@DisplayName("로그인")
	@Test
	void login() throws Exception {

		JwtDto jwtDto = new JwtDto(testJwt, testJwt);
		MemberResponse response = DtoDummy.memberResponse(17L);
		MemberJwtResponse memberJwtResponse = new MemberJwtResponse(response, jwtDto);
		LoginRequest loginRequest = DtoDummy.loginRequest();
		String requestBody = om.writeValueAsString(loginRequest);

		given(memberService.login(any(LoginRequest.class))).willReturn(memberJwtResponse);

		mockMvc.perform(
				post("/api/v1/members/login")
					.contentType(APPLICATION_JSON)
					.characterEncoding(StandardCharsets.UTF_8)
					.content(requestBody))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.id", is(response.getId()), Long.class));

		then(memberService).should(times(1)).login(any(LoginRequest.class));

	}

	@DisplayName("로그아웃")
	@Test
	void logout() throws Exception {

		mockMvc.perform(
				get("/api/v1/members/logout")
					.cookie(getCookie(testJwt, 0))
					.characterEncoding(StandardCharsets.UTF_8))
			.andExpect(status().isNoContent());
	}

	@DisplayName("회원 정보 업데이트")
	@Test
	void updateMember() throws Exception {

		MemberUpdateResponse response = DtoDummy.memberUpdateResponse();
		UpdateRequest request = DtoDummy.updateRequest();
		String requestBody = om.writeValueAsString(request);

		given(memberService.updateMember(anyLong(), any(UpdateRequest.class))).willReturn(response);

		mockMvc.perform(
				put("/api/v1/members")
					.cookie(getCookie(testJwt, 0))
					.contentType(APPLICATION_JSON)
					.characterEncoding(StandardCharsets.UTF_8)
					.content(requestBody)
			)
			.andExpect(status().isOk());

		then(memberService).should(times(1)).updateMember(anyLong(), any(UpdateRequest.class));

	}

	@DisplayName("회원 탈퇴")
	@Test
	void deleteMember() throws Exception {

		Long id = jwtTokenParser.getMemberClaims(testJwt).getMemberId();

		MemberResponse memberResponse = DtoDummy.memberResponse();
		MockHttpSession session = spy(new MockHttpSession());
		session.setAttribute(AUTH_INFO, memberResponse);
		session.setAttribute(REFRESH_TOKEN, "refresh token");

		willDoNothing().given(memberService).deleteMember(id);

		mockMvc.perform(
				delete("/api/v1/members")
					.cookie(getCookie(testJwt, 100))
					.characterEncoding(StandardCharsets.UTF_8)
					.session(session)
			)
			.andExpect(status().isNoContent());

		then(memberService).should(times(1)).deleteMember(id);
		then(session).should(times(1)).removeAttribute(AUTH_INFO);
		then(session).should(times(1)).removeAttribute(REFRESH_TOKEN);
		then(session).should(times(1)).invalidate();

	}

	@DisplayName("실명인증")
	@Test
	void retrieveIdentification() throws Exception {

		String name = "황동민";
		String rrnf = "970101";
		String rrnb = "1234567";

		given(memberService.retrieveIdentification(name, rrnf, rrnb)).willReturn(200);

		mockMvc.perform(
				get("/api/v1/members/identification")
					.param("name", name)
					.param("rrnf", rrnf)
					.param("rrnb", rrnb)
					.cookie(getCookie(testJwt, 0))
					.characterEncoding(StandardCharsets.UTF_8))
			.andExpect(status().isOk());

	}

}
