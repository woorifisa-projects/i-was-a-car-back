package xyz.iwasacar.api.domain.members.service;

import xyz.iwasacar.api.domain.members.dto.request.LoginRequest;
import xyz.iwasacar.api.domain.members.dto.request.SignupRequest;
import xyz.iwasacar.api.domain.members.dto.response.MemberJwtResponse;

public interface MemberService {

	MemberJwtResponse signup(final SignupRequest signupRequest);

	MemberJwtResponse login(final LoginRequest loginRequest);

}
