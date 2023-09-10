package xyz.iwasacar.api.domain.roles.repository;

import java.util.List;

import xyz.iwasacar.api.domain.roles.entity.MemberRole;

public interface MemberRoleRepositoryCustom {

	List<MemberRole> findByMemberId(Long memberId);

}
