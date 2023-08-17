package xyz.iwasacar.api.domain.members.service;

import xyz.iwasacar.api.domain.members.dto.request.LoginRequest;
import xyz.iwasacar.api.domain.members.dto.request.SignupRequest;
import xyz.iwasacar.api.domain.members.dto.response.MemberResponse;

public interface MemberService {

	MemberResponse signup(SignupRequest signupRequest);

	MemberResponse login(LoginRequest loginRequest);
	
}
