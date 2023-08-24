package xyz.iwasacar.api.domain.members.controller;

import static org.springframework.http.HttpStatus.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
	public MemberResponse signup(@Valid @RequestBody final SignupRequest signupRequest,
		final HttpServletResponse httpServletResponse, final HttpSession session) {

		MemberResponse memberResponse = memberService.signup(signupRequest);
		// Cookie 설정
		Cookie cookie = new Cookie("accessToken", memberResponse.getJwt().getAccessToken());

		cookie.setPath("/");
		cookie.setHttpOnly(true);
		cookie.setSecure(false);
		cookie.setMaxAge((int)(jwtTokenProvider.getRefreshTokenExpireTimeMils() / 1000));

		httpServletResponse.addCookie(cookie);

		// 리프레쉬 토크 세션 저장

		session.setAttribute("refreshToken", memberResponse.getJwt().getRefreshToken());

		return memberResponse;
		// 리턴 타입 변경이랑 쿠키저장
	}

	@PostMapping("/login")
	public ResponseEntity<CommonResponse<MemberResponse>> login(@Valid @RequestBody final LoginRequest loginRequest,
		final HttpServletResponse httpServletResponse, HttpSession session) {
		MemberResponse memberResponse = memberService.login(loginRequest);

		Cookie cookie = new Cookie("accessToken", memberResponse.getJwt().getAccessToken());

		cookie.setPath("/");
		cookie.setHttpOnly(true);
		cookie.setSecure(false);
		cookie.setMaxAge((int)(jwtTokenProvider.getRefreshTokenExpireTimeMils() / 1000));

		httpServletResponse.addCookie(cookie);

		// 리프레쉬 토크 세션 저장

		session.setAttribute("refreshToken", memberResponse.getJwt().getRefreshToken());

		return CommonResponse.success(OK, 200, memberResponse);
	}

	@GetMapping("/test")
	public void test(HttpServletRequest request) {
		String memberId = String.valueOf(request.getAttribute("Authorization"));
		System.out.println(memberId);
	}
}
