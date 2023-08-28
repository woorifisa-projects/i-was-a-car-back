package xyz.iwasacar.api.domain.members.controller;


import static org.springframework.http.HttpStatus.*;
import static xyz.iwasacar.api.common.auth.jwt.JwtUtil.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import xyz.iwasacar.api.common.auth.jwt.JwtDto;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenProvider;
import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.domain.members.dto.request.LoginRequest;
import xyz.iwasacar.api.domain.members.dto.request.SignupRequest;
import xyz.iwasacar.api.domain.members.dto.response.MemberJwtResponse;
import xyz.iwasacar.api.domain.members.dto.response.MemberResponse;
import xyz.iwasacar.api.domain.members.service.MemberService;


@RequestMapping("/api/v1/members")
@RestController
@RequiredArgsConstructor
public class MemberController {


	private final MemberService memberService;
	private final JwtTokenProvider jwtTokenProvider;

	@PostMapping("/signup")
	public ResponseEntity<CommonResponse<MemberResponse>> signup(
		@Valid @RequestBody final SignupRequest signupRequest,
		final HttpServletResponse response, final HttpSession session) {

		MemberJwtResponse memberJwtResponse = memberService.signup(signupRequest);
		settingAccessTokenCookie(memberJwtResponse.getJwtDto(), response, session);

		return CommonResponse.success(OK, OK.value(), memberJwtResponse.getMemberResponse());

	}

	@PostMapping("/login")
	public ResponseEntity<CommonResponse<MemberResponse>> login(
		@Valid @RequestBody final LoginRequest loginRequest,
		final HttpServletResponse response, final HttpSession session) {

		MemberJwtResponse memberJwtResponse = memberService.login(loginRequest);
		settingAccessTokenCookie(memberJwtResponse.getJwtDto(), response, session);

		return CommonResponse.success(OK, OK.value(), memberJwtResponse.getMemberResponse());
	}

	private void settingAccessTokenCookie(
		final JwtDto jwtDto, final HttpServletResponse resp, final HttpSession session) {

		Cookie cookie = new Cookie(ACCESS_TOKEN, jwtDto.getAccessToken());
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		cookie.setSecure(false);
		cookie.setMaxAge((int)(jwtTokenProvider.getRefreshTokenExpireTimeMils() / 1000));

		resp.addCookie(cookie);
		session.setAttribute(REFRESH_TOKEN, jwtDto.getRefreshToken());
	}

}
