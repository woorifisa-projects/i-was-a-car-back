package xyz.iwasacar.api.domain.members.controller;

import static org.springframework.http.HttpStatus.*;
import static xyz.iwasacar.api.common.auth.jwt.JwtUtil.*;

import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.annotation.Login;
import xyz.iwasacar.api.common.auth.email.EmailSession;
import xyz.iwasacar.api.common.auth.email.exception.InvalidEmailVerificationException;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenProvider;
import xyz.iwasacar.api.common.auth.jwt.MemberClaim;
import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.members.dto.request.LoginRequest;
import xyz.iwasacar.api.domain.members.dto.request.SignupRequest;
import xyz.iwasacar.api.domain.members.dto.request.UpdateRequest;
import xyz.iwasacar.api.domain.members.dto.response.AllMemberResponse;
import xyz.iwasacar.api.domain.members.dto.response.MemberDetailResponse;
import xyz.iwasacar.api.domain.members.dto.response.MemberJwtResponse;
import xyz.iwasacar.api.domain.members.dto.response.MemberResponse;
import xyz.iwasacar.api.domain.members.dto.response.MemberUpdateResponse;
import xyz.iwasacar.api.domain.members.service.MemberService;

@RequestMapping("/api/v1/members")
@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final JwtTokenProvider jwtTokenProvider;
	private final EmailSession emailSession;

	// 로그인 검증
	@GetMapping("/auth")
	public ResponseEntity<CommonResponse<MemberResponse>> authenticate(
		final HttpSession session, @Login final MemberClaim memberClaim) {

		MemberResponse memberResponse = (MemberResponse)session.getAttribute(AUTH_INFO);

		if (Objects.isNull(memberResponse)) {
			memberResponse = memberService.retrieveMemberInfo(memberClaim.getMemberId());
		}

		return CommonResponse.success(OK, OK.value(), memberResponse);
	}

	// 회원가입
	@PostMapping("/signup")
	public ResponseEntity<CommonResponse<MemberResponse>> signup(
		@RequestBody @Valid final SignupRequest signupRequest,
		final HttpServletResponse response, final HttpSession session) {

		if (!emailSession.verifyEmailCode(signupRequest.getEmail(), signupRequest.getCode())) {
			throw new InvalidEmailVerificationException();
		}

		MemberJwtResponse memberJwtResponse = memberService.signup(signupRequest);
		settingAccessTokenCookie(memberJwtResponse, response, session);

		emailSession.deleteEmailCode(signupRequest.getEmail());

		return CommonResponse.success(OK, OK.value(), memberJwtResponse.getMemberResponse());
	}

	// 로그인
	@PostMapping("/login")
	public ResponseEntity<CommonResponse<MemberResponse>> login(
		@Valid @RequestBody final LoginRequest loginRequest,
		final HttpServletResponse response, final HttpSession session) {
	
		MemberJwtResponse memberJwtResponse = memberService.login(loginRequest);
		settingAccessTokenCookie(memberJwtResponse, response, session);

		return CommonResponse.success(OK, OK.value(), memberJwtResponse.getMemberResponse());
	}

	// 로그아웃
	@GetMapping("/logout")
	public ResponseEntity<Void> logout(
		final HttpSession session,
		final HttpServletResponse response,
		@CookieValue(name = ACCESS_TOKEN) final Cookie accessToken) {

		if (accessToken != null) {
			accessToken.setValue(null);
			accessToken.setHttpOnly(true);
			accessToken.setSecure(false);
			accessToken.setPath("/");
			accessToken.setMaxAge(0);

			response.addCookie(accessToken);
			session.removeAttribute(AUTH_INFO);
			session.removeAttribute(REFRESH_TOKEN);
			session.invalidate();
		}

		return ResponseEntity.noContent().build();
	}

	// 회원 전체조회
	@GetMapping
	public ResponseEntity<CommonResponse<PageResponse<AllMemberResponse>>> findMembers(
		@RequestParam(defaultValue = "1") Integer page,
		@RequestParam(defaultValue = "8") Integer size
	) {

		PageResponse<AllMemberResponse> allMemberResponse = memberService.findMembers(page, size);

		return CommonResponse.success(OK, OK.value(), allMemberResponse);
	}

	// 회원 상세조회
	@GetMapping("/detail")
	public ResponseEntity<CommonResponse<MemberDetailResponse>> findMember(@RequestParam Long memberId) {

		MemberDetailResponse member = memberService.findMember(memberId);

		return CommonResponse.success(OK, OK.value(), member);
	}

	// 회원정보 수정
	@PutMapping
	public ResponseEntity<CommonResponse<MemberUpdateResponse>> updateMember(
		@Valid @RequestBody final UpdateRequest updateRequest,
		@Login final MemberClaim memberClaim) {

		MemberUpdateResponse memberUpdateResponse =
			memberService.updateMember(memberClaim.getMemberId(), updateRequest);

		return CommonResponse.success(OK, OK.value(), memberUpdateResponse);
	}

	// 회원 탈퇴
	@DeleteMapping
	public ResponseEntity<CommonResponse<Void>> deleteMember(
		@Login final MemberClaim memberClaim,
		final HttpSession session
	) {

		memberService.deleteMember(memberClaim.getMemberId());

		session.removeAttribute(AUTH_INFO);
		session.removeAttribute(REFRESH_TOKEN);
		session.invalidate();

		return CommonResponse.success(NO_CONTENT, NO_CONTENT.value(), null);
	}

	@GetMapping("/identification")
	public ResponseEntity<Void> retrieveIdentification(
		@RequestParam String name,
		@RequestParam String rrnf,
		@RequestParam String rrnb
	) {
		int statusCode = memberService.retrieveIdentification(name, rrnf, rrnb);
		return ResponseEntity.status(statusCode).build();
	}

	private void settingAccessTokenCookie(
		final MemberJwtResponse jwtDto, final HttpServletResponse resp, final HttpSession session) {

		Cookie cookie = new Cookie(ACCESS_TOKEN, jwtDto.getJwtDto().getAccessToken());
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		cookie.setSecure(false);
		cookie.setMaxAge((int)(jwtTokenProvider.getRefreshTokenExpireTimeMils() / 1000));

		resp.addCookie(cookie);
		session.setAttribute(REFRESH_TOKEN, jwtDto.getJwtDto().getRefreshToken());
		session.setAttribute(AUTH_INFO, jwtDto.getMemberResponse());
	}

}
