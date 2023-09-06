package xyz.iwasacar.api.domain.members.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.iwasacar.api.domain.members.entity.Member;
import xyz.iwasacar.api.domain.members.exception.MemberNotFoundException;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

	Optional<Member> findByEmail(final String email);

	default Member getBy(Long id) {
		return findById(id).orElseThrow(MemberNotFoundException::new);
	}

}
