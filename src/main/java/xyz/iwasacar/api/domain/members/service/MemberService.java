package xyz.iwasacar.api.domain.members.service;

import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.members.dto.request.LoginRequest;
import xyz.iwasacar.api.domain.members.dto.request.SignupRequest;
import xyz.iwasacar.api.domain.members.dto.request.UpdateRequest;
import xyz.iwasacar.api.domain.members.dto.response.AllMemberResponse;
import xyz.iwasacar.api.domain.members.dto.response.MemberDetailResponse;
import xyz.iwasacar.api.domain.members.dto.response.MemberJwtResponse;
import xyz.iwasacar.api.domain.members.dto.response.MemberUpdateResponse;

public interface MemberService {

	MemberJwtResponse signup(final SignupRequest signupRequest);

	MemberJwtResponse login(final LoginRequest loginRequest);

	PageResponse<AllMemberResponse> findMembers(Integer page, Integer size);

	MemberDetailResponse findMember(Long memberId);

	MemberUpdateResponse updateMember(Long memberId, UpdateRequest updateRequest);

	void deleteMember(Long memberId);

	boolean isDeletedMember(String email);
}
