package xyz.iwasacar.api.domain.roles.repository;

import java.util.List;

import xyz.iwasacar.api.domain.roles.entity.Role;

public interface RoleRepositoryCustom {

	List<Role> findRolesByMemberId(Long memberId);

}
