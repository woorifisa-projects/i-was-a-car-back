package xyz.iwasacar.api.domain.roles.repository;

import static xyz.iwasacar.api.domain.roles.entity.QMemberRole.*;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.roles.entity.MemberRole;

@RequiredArgsConstructor
public class MemberRoleRepositoryImpl implements MemberRoleRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<MemberRole> findByMemberId(final Long memberId) {
		return jpaQueryFactory
			.selectFrom(memberRole)
			.join(memberRole.role).fetchJoin()
			.where(memberRole.id.memberId.eq(memberId))
			.fetch();
	}

}
