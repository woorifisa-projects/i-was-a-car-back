package xyz.iwasacar.api.domain.members.repository;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.members.dto.response.AllMemberResponse;
import xyz.iwasacar.api.domain.members.entity.QMember;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Page<AllMemberResponse> findMembers(Integer page, Integer size) {
		QMember m = QMember.member;

		int offset = (page - 1) * size;
		//
		// private final String email;
		// private final String name;
		// private final String tel;
		// private final LocalDate birth;
		// private final Gender gender;
		// private final Boolean hasLicense;

		List<AllMemberResponse> list = jpaQueryFactory.select(Projections.constructor(
				AllMemberResponse.class, m.email, m.name, m.tel, m.birth, m.gender, m.hasLicense))
			.from(m)
			.orderBy(m.id.desc())
			.offset(offset)
			.limit(size)
			.fetch();

		long totalCount = Objects.requireNonNull(
			jpaQueryFactory
				.select(m.count())
				.from(m)
				.fetchOne());

		return new PageImpl<>(list, PageRequest.of(page - 1, size), totalCount);
	}
}
