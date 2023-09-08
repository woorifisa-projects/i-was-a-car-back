package xyz.iwasacar.api.domain.members.service;

import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.members.dto.request.LoginRequest;
import xyz.iwasacar.api.domain.members.dto.request.MemberUpdateRequest;
import xyz.iwasacar.api.domain.members.dto.request.SignupRequest;
import xyz.iwasacar.api.domain.members.dto.request.UpdateRequest;
import xyz.iwasacar.api.domain.members.dto.response.AdminMemberUpdateResponse;
import xyz.iwasacar.api.domain.members.dto.response.AllMemberResponse;
import xyz.iwasacar.api.domain.members.dto.response.MemberDetailResponse;
import xyz.iwasacar.api.domain.members.dto.response.MemberJwtResponse;
import xyz.iwasacar.api.domain.members.dto.response.MemberResponse;
import xyz.iwasacar.api.domain.members.dto.response.MemberUpdateResponse;

public interface MemberService {

	MemberJwtResponse signup(final SignupRequest signupRequest);

	MemberJwtResponse login(final LoginRequest loginRequest);

	PageResponse<AllMemberResponse> findMembers(Integer page, Integer size);

	MemberDetailResponse findMember(Long memberId);

	AdminMemberUpdateResponse updateMember(Long memberId, MemberUpdateRequest memberUpdateRequest);

	void deleteMember(Long memberId);

	MemberUpdateResponse updateMember(Long memberId, UpdateRequest updateRequest);

	boolean isDeletedMember(String email);

	int retrieveIdentification(String name, String rrnf, String rrnb);

	MemberResponse retrieveMemberInfo(Long id);
}
