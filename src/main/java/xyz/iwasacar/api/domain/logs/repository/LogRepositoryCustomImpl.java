package xyz.iwasacar.api.domain.logs.repository;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.logs.dto.LogResponse;
import xyz.iwasacar.api.domain.logs.entity.QLog;

@RequiredArgsConstructor
public class LogRepositoryCustomImpl implements LogRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Page<LogResponse> findLogs(int page, int size) {
		QLog log = QLog.log1;
		int offset = (page - 1) * size;

		List<LogResponse> list = jpaQueryFactory
			.select(Projections.constructor(
				LogResponse.class, log.id, log.member.name, log.member.email, log.log, log.createdAt)
			).from(log)
			.join(log.member)
			.orderBy(log.id.desc())
			.offset(offset)
			.limit(size)
			.fetch();

		long totalCount = Objects.requireNonNull(
			jpaQueryFactory
				.select(log.count())
				.from(log)
				.fetchOne());

		return new PageImpl<>(list, PageRequest.of(page - 1, size), totalCount);
	}

}
