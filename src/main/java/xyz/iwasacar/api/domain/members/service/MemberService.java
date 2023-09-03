package xyz.iwasacar.api.domain.members.service;

import org.springframework.stereotype.Service;

import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.members.dto.request.LoginRequest;
import xyz.iwasacar.api.domain.members.dto.request.MemberUpdateRequest;
import xyz.iwasacar.api.domain.members.dto.request.SignupRequest;
import xyz.iwasacar.api.domain.members.dto.response.AllMemberResponse;
import xyz.iwasacar.api.domain.members.dto.response.MemberDetailResponse;
import xyz.iwasacar.api.domain.members.dto.response.MemberJwtResponse;
import xyz.iwasacar.api.domain.members.dto.response.MemberUpdateResponse;

@Service
public interface MemberService {

	MemberJwtResponse signup(final SignupRequest signupRequest);

	MemberJwtResponse login(final LoginRequest loginRequest);

	PageResponse<AllMemberResponse> findMembers(Integer page, Integer size);

	MemberDetailResponse findMember(Long memberId);

	MemberUpdateResponse updateMember(Long memberId, MemberUpdateRequest memberUpdateRequest);

	void deleteMember(Long memberId);
}
