package xyz.iwasacar.api.domain.documentitems.repository;

import static xyz.iwasacar.api.domain.documentitems.entity.QDocumentItem.*;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.common.constant.EntityStatus;
import xyz.iwasacar.api.domain.documentitems.entity.DocumentItem;

@RequiredArgsConstructor
public class DocumentItemRepositoryImpl implements DocumentItemRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<DocumentItem> findByDocumentId(Long documentId) {

		return jpaQueryFactory
			.selectFrom(documentItem)
			.join(documentItem.document).fetchJoin()
			.where(documentItem.document.id.eq(documentId)
				.and(documentItem.status.ne(EntityStatus.DELETED)))
			.orderBy(documentItem.order.asc())
			.fetch();
	}

	@Override
	public List<DocumentItem> findAllInId(List<Long> id) {
		return jpaQueryFactory
			.selectFrom(documentItem)
			.where(documentItem.document.id.in(id)
				.and(documentItem.status.ne(EntityStatus.DELETED)))
			.orderBy(documentItem.order.asc())
			.fetch();
	}
}
