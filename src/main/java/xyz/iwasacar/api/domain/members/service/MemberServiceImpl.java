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
import xyz.iwasacar.api.domain.members.exception.UnauthorizedException;
import xyz.iwasacar.api.domain.members.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	private final JwtTokenProvider jwtTokenProvider;

	private final PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public MemberResponse signup(final SignupRequest signupRequest) {

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
			.status(EntityStatus.CREATED)
			.build();

		Member savedMember = memberRepository.save(member);

		// JWT
		Map<String, Object> claims = new HashMap<>();

		Jwt jwt = jwtTokenProvider.createJwt(claims, member.getId());

		// jwt - RefreshToken -> 서버에서 저장
		
		// 반환 - Member + AccessToken

		// return response DTO
		return new MemberResponse(savedMember, jwt);
	}

	@Transactional
	@Override
	public MemberResponse login(final LoginRequest loginRequest) {

		Member memberEntity = memberRepository.findByEmail(loginRequest.getEmail())
			.orElseThrow(IllegalArgumentException::new);

		if (passwordEncoder.matches(loginRequest.getPassword(), memberEntity.getPassword())) {

			Map<String, Object> claims = new HashMap<>();
			claims.put("memberId", memberEntity.getId()); // 역할로 변경해야 됨
			Jwt jwt = jwtTokenProvider.createJwt(claims, memberEntity.getId());

			memberEntity.updateLastLoginAt();

			return new MemberResponse(memberEntity, jwt);
		}

		throw new UnauthorizedException();
	}

}
