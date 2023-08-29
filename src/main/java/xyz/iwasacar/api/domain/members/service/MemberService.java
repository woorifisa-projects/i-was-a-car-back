package xyz.iwasacar.api.domain.members.service;

import java.util.List;

import xyz.iwasacar.api.domain.members.dto.request.LoginRequest;
import xyz.iwasacar.api.domain.members.dto.request.SignupRequest;
import xyz.iwasacar.api.domain.members.dto.response.AllMemberResponse;
import xyz.iwasacar.api.domain.members.dto.response.MemberJwtResponse;

public interface MemberService {

	MemberJwtResponse signup(final SignupRequest signupRequest);

	MemberJwtResponse login(final LoginRequest loginRequest);

	List<AllMemberResponse> findMembers();
}
