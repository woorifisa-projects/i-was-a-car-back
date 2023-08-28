package xyz.iwasacar.api.domain.resources.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.iwasacar.api.domain.common.constant.EntityStatus;

@Table(name = "resources")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Resource {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "resource_no")
	private Long id;

	@Column(name = "url", nullable = false)
	private String url;

	@Column(name = "status", nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private EntityStatus status;

	@Column(name = "created_at", nullable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "original_name", nullable = false)
	private String originalName;

	@Column(name = "updated_at", nullable = false)
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

	@Builder
	public Resource(String url, EntityStatus status, String originalName) {
		this.url = url;
		this.status = status;
		this.originalName = originalName;
	}

	public Resource(String url, String originalName) {
		this.url = url;
		this.status = EntityStatus.CREATED;
		this.originalName = originalName;
	}

}
