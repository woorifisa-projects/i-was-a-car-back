package xyz.iwasacar.api.domain.members.repository;

import org.springframework.data.domain.Page;

import xyz.iwasacar.api.domain.members.dto.response.AllMemberResponse;

public interface MemberRepositoryCustom {

	Page<AllMemberResponse> findMembers(Integer page, Integer size);
}
