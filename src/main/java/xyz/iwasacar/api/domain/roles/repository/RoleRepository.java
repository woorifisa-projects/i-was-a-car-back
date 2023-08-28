package xyz.iwasacar.api.domain.roles.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.roles.entity.Role;
import xyz.iwasacar.api.domain.roles.entity.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long>, RoleRepositoryCustom {

	Optional<Role> findByName(RoleName name);

}
