package xyz.iwasacar.api.domain.histories.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
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
import xyz.iwasacar.api.domain.insurances.entity.Insurance;
import xyz.iwasacar.api.domain.loans.entity.Loan;
import xyz.iwasacar.api.domain.members.entity.Member;
import xyz.iwasacar.api.domain.products.entity.Product;

@Entity
@Table(name = "purchases_histories")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PurchaseHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchase_history_no")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "member_no", nullable = false)
	private Member member;

	@ManyToOne
	@JoinColumn(name = "product_no", nullable = false)
	private Product product;

	@ManyToOne
	@JoinColumn(name = "bank_no", nullable = false)
	private Bank bank;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loan_no", nullable = false)
	private Loan loan;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "insurance_no", nullable = false)
	private Insurance insurance;

	@Column(name = "zip_code", nullable = false)
	private String zipCode;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "address_detail", nullable = false)
	private String addressDetail;

	@Column(name = "account_number", nullable = false, length = 20)
	private String accountNumber;

	@Column(name = "account_holder", nullable = false, length = 20)
	private String accountHolder;

	@Column(name = "loan_amount", nullable = false)
	private Integer loanAmount;

	@Column(name = "period", nullable = false)
	private Integer period;

	@Column(name = "delivery_schedule", nullable = false)
	private LocalDateTime deliverySchedule;

	@Column(name = "status", nullable = false, length = 20)
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
	public PurchaseHistory(Member member, Product product, Bank bank, Loan loan, Insurance insurance, String zipCode,
		String address, String addressDetail, String accountNumber, String accountHolder, Integer loanAmount,
		Integer period, LocalDateTime deliverySchedule, EntityStatus status) {

		this.member = member;
		this.product = product;
		this.bank = bank;
		this.loan = loan;
		this.insurance = insurance;
		this.zipCode = zipCode;
		this.address = address;
		this.addressDetail = addressDetail;
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
		this.loanAmount = loanAmount;
		this.period = period;
		this.deliverySchedule = deliverySchedule;
		this.status = status;
	}
}
