package xyz.iwasacar.api.domain.members.service;

import static java.util.stream.Collectors.*;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.auth.jwt.JwtDto;
import xyz.iwasacar.api.common.auth.jwt.JwtTokenProvider;
import xyz.iwasacar.api.common.auth.jwt.MemberClaim;
import xyz.iwasacar.api.common.component.PasswordEncoder;
import xyz.iwasacar.api.common.dto.response.PageResponse;
import xyz.iwasacar.api.domain.common.constant.EntityStatus;
import xyz.iwasacar.api.domain.members.client.MemberClient;
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
import xyz.iwasacar.api.domain.members.entity.Member;
import xyz.iwasacar.api.domain.members.exception.MemberNotFoundException;
import xyz.iwasacar.api.domain.members.exception.UnauthorizedException;
import xyz.iwasacar.api.domain.members.repository.MemberRepository;
import xyz.iwasacar.api.domain.roles.entity.MemberRole;
import xyz.iwasacar.api.domain.roles.entity.Role;
import xyz.iwasacar.api.domain.roles.entity.RoleName;
import xyz.iwasacar.api.domain.roles.exception.RoleNotFoundException;
import xyz.iwasacar.api.domain.roles.repository.MemberRoleRepository;
import xyz.iwasacar.api.domain.roles.repository.RoleRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final RoleRepository roleRepository;
	private final MemberRoleRepository memberRoleRepository;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;
	private final MemberClient memberClient;

	@Transactional
	@Override
	public MemberJwtResponse signup(final SignupRequest signupRequest) {

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

		Role role = roleRepository.findByName(RoleName.MEMBER)
			.orElseThrow(RoleNotFoundException::new);

		MemberRole savedMemberRole = memberRoleRepository.save(new MemberRole(role, member));
		List<RoleName> roles = List.of(savedMemberRole.getRole().getName());

		MemberClaim memberClaim = new MemberClaim(member.getId(), roles);

		JwtDto jwtDto = jwtTokenProvider.createJwt(memberClaim);

		return new MemberJwtResponse(savedMember, jwtDto, roles);
	}

	@Transactional
	@Override
	public MemberJwtResponse login(final LoginRequest loginRequest) {

		Member member = memberRepository.findByEmail(loginRequest.getEmail())
			.orElseThrow(MemberNotFoundException::new);

		if (member.isDeleted() || !passwordEncoder.matches(loginRequest.getPassword(), member.getPassword())) {
			throw new UnauthorizedException();
		}

		member.updateLastLogin();

		List<RoleName> roles = roleRepository.findRolesByMemberId(member.getId())
			.stream()
			.map(Role::getName)
			.collect(toList());

		JwtDto jwtDto = jwtTokenProvider.createJwt(new MemberClaim(member.getId(), roles));

		return new MemberJwtResponse(member, jwtDto, roles);
	}

	@Override
	public MemberResponse retrieveMemberInfo(final Long id) {
		Member by = memberRepository.getBy(id);
		List<RoleName> roles = memberRoleRepository.findByMemberId(id)
			.stream()
			.map(mr -> mr.getRole().getName())
			.collect(toList());

		return MemberResponse.from(by, roles);
	}

	@Override
	public PageResponse<AllMemberResponse> findMembers(final Integer page, final Integer size) {

		Page<AllMemberResponse> allMemberResponses = memberRepository.findMembers(page, size);
		return new PageResponse<>(allMemberResponses.getContent(), page, allMemberResponses.getTotalPages());
	}

	@Override
	public MemberDetailResponse findMember(final Long memberId) {

		return MemberDetailResponse.from(memberRepository.getBy(memberId));
	}

	public AdminMemberUpdateResponse updateMember(final Long memberId, final MemberUpdateRequest memberUpdateRequest) {
		Member member = memberRepository.getBy(memberId);
		member.update(memberUpdateRequest.getGender(), member.getTel(), memberUpdateRequest.getHasLicense());

		return AdminMemberUpdateResponse.from(member);
	}

	@Transactional
	public void deleteMember(final Long memberId) {
		memberRepository.getBy(memberId).delete();
	}

	@Transactional
	public MemberUpdateResponse updateMember(final Long memberId,
		final UpdateRequest updateRequest) {

		Member member = memberRepository.findById(memberId)
			.orElseThrow(MemberNotFoundException::new);

		String encodedPassword = passwordEncoder.encode(updateRequest.getPassword());

		member.update(updateRequest, encodedPassword);

		return MemberUpdateResponse.from(member);
	}

	@Override
	public boolean isDeletedMember(final String email) {

		Member member = memberRepository.findByEmail(email)
			.orElseThrow(MemberNotFoundException::new);

		return member.getStatus().equals(EntityStatus.DELETED);
	}

	public int retrieveIdentification(final String name, final String rrnf, final String rrnb) {

		return memberClient.retrieveIdentification(name, rrnf, rrnb).getStatusCodeValue();
	}

}
