package xyz.iwasacar.api.domain.members.controller.admin;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.dto.response.CommonResponse;
import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.members.dto.request.MemberUpdateRequest;
import xyz.iwasacar.api.domain.members.dto.response.AdminMemberUpdateResponse;
import xyz.iwasacar.api.domain.members.dto.response.AllMemberResponse;
import xyz.iwasacar.api.domain.members.dto.response.MemberDetailResponse;
import xyz.iwasacar.api.domain.members.service.MemberService;

@RestController
@RequestMapping("/api/v1/admin/members")
@RequiredArgsConstructor
public class AdminMemberController {

	private final MemberService memberService;

	@GetMapping
	public ResponseEntity<CommonResponse<PageResponse<AllMemberResponse>>> findMembers(
		@RequestParam(required = false, defaultValue = "1") Integer page,
		@RequestParam(required = false, defaultValue = "10") Integer size
	) {
		PageResponse<AllMemberResponse> members = memberService.findMembers(page, size);
		return CommonResponse.success(OK, OK.value(), members);
	}

	@GetMapping("/{memberId}")
	public ResponseEntity<CommonResponse<MemberDetailResponse>> findMemberById(@PathVariable Long memberId) {
		MemberDetailResponse member = memberService.findMember(memberId);
		return CommonResponse.success(OK, OK.value(), member);
	}

	@PutMapping("/{memberId}")
	public ResponseEntity<CommonResponse<AdminMemberUpdateResponse>> updateMember(
		@PathVariable Long memberId,
		@RequestBody MemberUpdateRequest memberUpdateRequest) {

		AdminMemberUpdateResponse memberUpdateResponse = memberService.updateMember(memberId, memberUpdateRequest);

		return CommonResponse.success(OK, OK.value(), memberUpdateResponse);
	}

	@DeleteMapping("/{memberId}")
	public ResponseEntity<CommonResponse<Void>> deleteMember(@PathVariable Long memberId) {
		memberService.deleteMember(memberId);
		return CommonResponse.success(NO_CONTENT, NO_CONTENT.value(), null);
	}

}
