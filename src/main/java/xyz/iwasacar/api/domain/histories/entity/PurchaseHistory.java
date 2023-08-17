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

import lombok.AccessLevel;
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
	private Integer zipCode;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "address_detail", nullable = false)
	private String addressDetail;

	@Column(name = "account_holder", nullable = false, length = 20)
	private String accountHolder;

	@Column(name = "delivery_schedule", nullable = false)
	private LocalDateTime deliverySchedule;

	@Column(name = "status", nullable = false, length = 20)
	private EntityStatus status;

	@Column(name = "create_at", nullable = false)
	@CreationTimestamp
	private LocalDateTime createAt;

}
