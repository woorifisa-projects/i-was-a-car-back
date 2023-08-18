package xyz.iwasacar.api.domain.members.controller;

import javax.servlet.http.Cookie;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenProvider;
import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.domain.members.dto.request.LoginRequest;
import xyz.iwasacar.api.domain.members.dto.request.SignupRequest;
import xyz.iwasacar.api.domain.members.dto.response.MemberResponse;
import xyz.iwasacar.api.domain.members.service.MemberService;

@RequestMapping("/api/v1/members")
@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	private final JwtTokenProvider jwtTokenProvider;

	@PostMapping("/signup")
	public MemberResponse signup(@Valid @RequestBody SignupRequest signupRequest) {
		return memberService.signup(signupRequest);
	}

	@PostMapping("/login")
	public ResponseEntity<CommonResponse<MemberResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {

		MemberResponse memberResponse = memberService.login(loginRequest);

		// Cookie 설정
		Cookie cookie = new Cookie("accessToken", memberResponse.getJwt().getAccessToken());
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		cookie.setSecure(false);
		cookie.setMaxAge((int)jwtTokenProvider.getAccessTokenExpireTimeMils());

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Set-Cookie", cookie.toString());

		CommonResponse<MemberResponse> commonResponse = new CommonResponse<>(200, memberResponse);

		return new ResponseEntity<>(commonResponse, httpHeaders, HttpStatus.OK);

	}
}
