package xyz.iwasacar.api.domain.histories.entity;

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
import xyz.iwasacar.api.domain.banks.entity.Bank;
import xyz.iwasacar.api.domain.common.constant.EntityStatus;
import xyz.iwasacar.api.domain.members.entity.Member;
import xyz.iwasacar.api.domain.products.entity.Product;

@Entity
@Table(name = "sales_histories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SaleHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sales_history_no")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_no", nullable = false)
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_no", nullable = false)
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bank_no", nullable = false)
	private Bank bank;

	@Column(name = "meeting_schedule", nullable = false)
	private LocalDateTime meetingSchedule;

	@Column(name = "account_number", nullable = false, length = 20)
	private String accountNumber;

	@Column(name = "account_holder", nullable = false, length = 20)
	private String accountHolder;

	@Column(name = "zip_code", nullable = false, length = 20)
	private String zipCode;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "address_detail", nullable = false)
	private String addressDetail;

	@Column(name = "status", nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private EntityStatus status;

	@Column(name = "created_at", nullable = false)
	@CreationTimestamp
	private LocalDateTime createAt;

	@Column(name = "updated_at", nullable = false)
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

	@Builder
	public SaleHistory(Product product, Member member, Bank bank, LocalDateTime meetingSchedule, String accountNumber,
		String accountHolder, String zipCode, String address, String addressDetail) {
		this.product = product;
		this.member = member;
		this.bank = bank;
		this.meetingSchedule = meetingSchedule;
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
		this.zipCode = zipCode;
		this.address = address;
		this.addressDetail = addressDetail;
		this.status = EntityStatus.CREATED;
	}

}
