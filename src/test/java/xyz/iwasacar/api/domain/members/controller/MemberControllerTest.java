package xyz.iwasacar.api.domain.members.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static xyz.iwasacar.api.common.auth.jwt.JwtUtil.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import xyz.iwasacar.api.common.argumentresolver.MemberClaimArgumentResolver;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenParser;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenProvider;
import xyz.iwasacar.api.common.interceptor.BearerAuthInterceptor;

@WebMvcTest(MemberController.class)
class MemberControllerTest {

	MockMvc mockMvc;

	@Autowired
	MemberController memberController;

	@Autowired
	JwtTokenParser jwtTokenParser;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

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
		MockHttpSession session = new MockHttpSession();
		session.setAttribute(AUTH_INFO);
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
