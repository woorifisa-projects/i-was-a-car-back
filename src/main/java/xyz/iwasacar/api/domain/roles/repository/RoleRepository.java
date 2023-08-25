package xyz.iwasacar.api.domain.roles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.roles.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
