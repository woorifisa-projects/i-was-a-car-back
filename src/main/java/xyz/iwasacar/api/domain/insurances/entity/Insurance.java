package xyz.iwasacar.api.domain.insurances.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "insurances")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Insurance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "insurance_no")
	private Long id;

	@Column(name = "name", nullable = false, length = 20)
	private String name;

	@Column(name = "company", nullable = false, length = 20)
	private String company;

	@Column(name = "period", nullable = false)
	private Integer period;

	@Column(name = "monthly_premium", nullable = false)
	private Integer monthlyPremium;

	@Builder
	public Insurance(String name, String company, Integer period, Integer monthlyPremium) {
		this.name = name;
		this.company = company;
		this.period = period;
		this.monthlyPremium = monthlyPremium;
	}
}
