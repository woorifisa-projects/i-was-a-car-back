package xyz.iwasacar.api.domain.members.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.members.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findByEmail(final String email);
}
