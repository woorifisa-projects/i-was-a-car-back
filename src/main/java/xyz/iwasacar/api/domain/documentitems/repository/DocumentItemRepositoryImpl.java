package xyz.iwasacar.api.domain.documentitems.repository;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.documentitems.entity.DocumentItem;
import xyz.iwasacar.api.domain.documentitems.entity.QDocumentItem;

@RequiredArgsConstructor
public class DocumentItemRepositoryImpl implements DocumentItemRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<DocumentItem> findByDocumentId(Long documentId) {
		QDocumentItem documentItem = QDocumentItem.documentItem;

		return jpaQueryFactory
			.selectFrom(documentItem)
			.where(documentItem.document.id.eq(documentId))
			.orderBy(documentItem.order.asc())
			.fetch();
	}
}
