package xyz.iwasacar.api.domain.roles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.roles.entity.MemberRole;

public interface MemberRoleRepository extends JpaRepository<MemberRole, Long>, MemberRoleRepositoryCustom {

}
