package xyz.iwasacar.api.domain.logs.entity;

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
import xyz.iwasacar.api.domain.members.entity.Member;

@Table(name = "logs")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Log {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "log_no")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_no", nullable = false)
	private Member member;

	@Column(name = "log", nullable = false)
	private String log;

	@Column(name = "created_at", nullable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;

	public Log(Member member, String log) {
		this.member = member;
		this.log = log;
	}

	public static Log login(Member member) {
		return new Log(member, "로그인");
	}

	public static Log logout(Member member) {
		return new Log(member, "로그아웃");
	}

	public static Log update(Member member) {
		return new Log(member, "회원정보수정");
	}

	public static Log purchase(Member member, Long id) {
		return new Log(member, "구매번호: " + id);
	}

	public static Log sale(Member member, Long id) {
		return new Log(member, "판매번호: " + id);
	}

}
