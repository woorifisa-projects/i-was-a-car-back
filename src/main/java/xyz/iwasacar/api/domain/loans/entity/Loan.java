package xyz.iwasacar.api.domain.loans.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "loans")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loan_no")
	private Long id;

	@Column(name = "name", nullable = false, length = 20)
	private String name;

	@Column(name = "content", nullable = false)
	private String content;

	@Column(name = "target", nullable = false, length = 20)
	private String target;

	@Column(name = "\"limit\"", nullable = false)
	private Integer limit;

	@Column(name = "interest_rate", nullable = false)
	private Double interestRate;

	@Column(name = "redemption", nullable = false, length = 20)
	private String redemption;

	@Column(name = "period", nullable = false)
	private Integer period;

	@Column(name = "grace_period", nullable = false)
	private Integer gracePeriod;

	@Column(name = "early_redemption_charge")
	private String earlyRedemptionCharge;

	@Column(name = "overdue_interest_rate")
	private String overdueInterestRate;

	@Column(name = "document")
	private String document;

	@Column(name = "interest_charged_day")
	private String interestChargedDay;

	@Column(name = "additional_fee")
	private String additionalFee;

}
