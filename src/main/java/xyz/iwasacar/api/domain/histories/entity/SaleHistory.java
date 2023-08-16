package xyz.iwasacar.api.domain.histories.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	@ManyToOne
	@JoinColumn(name = "product_no", nullable = false)
	private Product product;

	@ManyToOne
	@JoinColumn(name = "member_no", nullable = false)
	private Member member;

	@ManyToOne
	@JoinColumn(name = "bank_no", nullable = false)
	private Bank bank;

	@Column(name = "meeting_schedule", nullable = false)
	private LocalDateTime meetingSchedule;

	@Column(name = "account_number", nullable = false, length = 20)
	private String accountNumber;

	@Column(name = "account_holder", nullable = false, length = 20)
	private String accountHolder;

	@Column(name = "zip_code", nullable = false)
	private Integer zipCode;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "address_detail", nullable = false)
	private String addressDetail;

	@Column(name = "status", nullable = false, length = 20)
	private EntityStatus status;

	@Column(name = "create_at", nullable = false)
	@CreationTimestamp
	private LocalDateTime createAt;

}
