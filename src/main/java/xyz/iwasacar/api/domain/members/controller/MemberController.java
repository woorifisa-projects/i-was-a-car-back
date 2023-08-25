package xyz.iwasacar.api.domain.members.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.domain.members.dto.response.MemberResponse;

@RequestMapping("/api/v1/members")
@RestController
@RequiredArgsConstructor
public class MemberController {

	@PostMapping("/login")
	public ResponseEntity<CommonResponse<MemberResponse>> login() {
		System.out.println("login method");
		return ResponseEntity.ok().build();
	}

}
