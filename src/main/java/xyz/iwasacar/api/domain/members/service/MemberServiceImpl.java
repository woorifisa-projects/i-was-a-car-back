package xyz.iwasacar.api.domain.members.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.auth.jwt.Jwt;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenProvider;
import xyz.iwasacar.api.common.component.PasswordEncoder;
import xyz.iwasacar.api.domain.common.constant.EntityStatus;
import xyz.iwasacar.api.domain.members.dto.request.LoginRequest;
import xyz.iwasacar.api.domain.members.dto.request.SignupRequest;
import xyz.iwasacar.api.domain.members.dto.response.MemberResponse;
import xyz.iwasacar.api.domain.members.entity.Member;
import xyz.iwasacar.api.domain.members.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	private final JwtTokenProvider jwtTokenProvider;

	private final PasswordEncoder passwordEncoder;

	@Transactional
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
		Map<String, Object> claims = new HashMap<>();
		claims.put("memberId", savedMember.getId());

		Jwt jwt = jwtTokenProvider.createJwt(claims);

		// return response DTO
		return new MemberResponse(savedMember, jwt);
	}

	@Transactional
	@Override
	public MemberResponse login(LoginRequest loginRequest) {

		Member memberEntity = memberRepository.findByEmail(loginRequest.getEmail())
			.orElseThrow(IllegalArgumentException::new);

		if (passwordEncoder.matches(loginRequest.getPassword(), memberEntity.getPassword())) {
			// 로그인 할 때 마다 이거 이렇게 계속 갱신 시켜줘야 함?
			Map<String, Object> claims = new HashMap<>();
			claims.put("memberId", memberEntity.getId());
			Jwt jwt = jwtTokenProvider.createJwt(claims);

			memberEntity.updateLastLoginAt();

			return new MemberResponse(memberEntity, jwt);
		}

		return null;
	}

}
