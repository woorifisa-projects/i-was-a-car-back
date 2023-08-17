package xyz.iwasacar.api.domain.members.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.auth.jwt.JwtProvider;
import xyz.iwasacar.api.common.component.PasswordEncoder;
import xyz.iwasacar.api.domain.common.constant.EntityStatus;
import xyz.iwasacar.api.domain.members.dto.request.SignupRequest;
import xyz.iwasacar.api.domain.members.dto.response.MemberResponse;
import xyz.iwasacar.api.domain.members.entity.Member;
import xyz.iwasacar.api.domain.members.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	private final JwtProvider jwt;

	private final PasswordEncoder passwordEncoder;

	@Override
	public MemberResponse signup(SignupRequest signupRequest) {

		String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());

		Member member = Member.builder()
			.email(signupRequest.getEmail())
			.password(encodedPassword)
			.birth(signupRequest.getBirth())
			.gender(signupRequest.getGender())
			.hasLicense(signupRequest.getHasLicense())
			.tel(signupRequest.getTel())
			.lastLoginAt(LocalDateTime.now())
			.name(signupRequest.getName())
			.status(EntityStatus.생성)
			.build();

		Member savedMember = memberRepository.save(member);

		// JWT

		// return response DTO
		return new MemberResponse(savedMember);
	}
}
