package xyz.iwasacar.api.domain.roles.repository;

import static xyz.iwasacar.api.domain.roles.entity.QMemberRole.*;
import static xyz.iwasacar.api.domain.roles.entity.QRole.*;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.roles.entity.Role;

@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<Role> findRolesByMemberId(final Long memberId) {

		return jpaQueryFactory
			.select(role)
			.from(memberRole)
			.where(memberRole.id.memberId.eq(memberId))
			.fetch();
	}

}
