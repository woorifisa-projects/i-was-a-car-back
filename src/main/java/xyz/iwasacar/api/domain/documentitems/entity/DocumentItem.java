package xyz.iwasacar.api.domain.documentitems.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.iwasacar.api.domain.common.constant.EntityStatus;
import xyz.iwasacar.api.domain.documents.entity.Document;

@Table(name = "document_items")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DocumentItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "document_item_no")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_no", nullable = false)
	private Document document;

	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "\"order\"", nullable = false)
	private Integer order;

	@Column(name = "status", nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private EntityStatus status;

	@Column(name = "created_at", nullable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

}
