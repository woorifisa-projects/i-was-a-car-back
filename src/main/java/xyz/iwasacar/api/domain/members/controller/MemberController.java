package xyz.iwasacar.api.domain.members.controller;

import static org.springframework.http.HttpStatus.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ResponseEntity<CommonResponse<MemberResponse>> login(@Valid @RequestBody LoginRequest loginRequest,
		HttpServletResponse httpServletResponse) {

		MemberResponse memberResponse = memberService.login(loginRequest);

		// Cookie 설정
		Cookie cookie = new Cookie("accessToken", memberResponse.getJwt().getAccessToken());
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		cookie.setSecure(false);
		cookie.setMaxAge((int)(jwtTokenProvider.getAccessTokenExpireTimeMils() / 1000));

		httpServletResponse.addCookie(cookie);

		return CommonResponse.success(OK, 200, memberResponse);
	}

	@GetMapping("/test")
	public void test(HttpServletRequest request) {
		String memberId = String.valueOf(request.getAttribute("Authorization"));
		System.out.println(memberId);
	}
}
