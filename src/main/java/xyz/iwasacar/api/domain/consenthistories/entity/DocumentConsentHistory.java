package xyz.iwasacar.api.domain.consenthistories.entity;

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
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.iwasacar.api.domain.common.constant.EntityStatus;
import xyz.iwasacar.api.domain.documentitems.entity.DocumentItem;
import xyz.iwasacar.api.domain.members.entity.Member;
import xyz.iwasacar.api.domain.products.entity.Product;

@Table(name = "document_consent_histories")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DocumentConsentHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "consent_history_no")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_no", nullable = false)
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_no")
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_item_no", nullable = false)
	private DocumentItem documentItem;

	@Column(name = "consent", nullable = false)
	private Boolean consent;

	@Column(name = "status", nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private EntityStatus status;

	@Column(name = "created_at", nullable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = false)
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

	@Builder
	public DocumentConsentHistory(Member member, Product product, DocumentItem documentItem, Boolean consent) {
		this.member = member;
		this.product = product;
		this.documentItem = documentItem;
		this.consent = consent;
		this.status = EntityStatus.CREATED;
	}

	public static DocumentConsentHistory signupConsent(Member member, DocumentItem documentItem, Boolean consent) {
		return DocumentConsentHistory.builder()
			.member(member)
			.documentItem(documentItem)
			.product(null)
			.consent(consent)
			.build();
	}

	public static DocumentConsentHistory contractConsent(Member member, DocumentItem documentItem, Product product,
		Boolean consent) {
		return DocumentConsentHistory.builder()
			.member(member)
			.documentItem(documentItem)
			.product(product)
			.consent(consent)
			.build();
	}

}
