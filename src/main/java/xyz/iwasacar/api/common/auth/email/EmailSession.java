package xyz.iwasacar.api.common.auth.email;

import java.time.Duration;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.auth.email.exception.EmailCodeNotFoundException;

@Component
@RequiredArgsConstructor
public class EmailSession {

	private static final String TRUE = "true";

	private static final String FALSE = "false";

	private final StringRedisTemplate stringRedisTemplate;

	private static final long DURATION = 60 * 5 * 1L; // 5분

	public void setEmailCode(String email, String code) {

		stringRedisTemplate.opsForHash().put(email, code, FALSE);

		// 유효시간 설정
		stringRedisTemplate.expire(email, Duration.ofSeconds(DURATION));
	}

	// 회원가입 시 true인지 값을 확인하기 위한 메서드
	public boolean verifyEmailCode(String email, String code) {

		String value = (String)stringRedisTemplate.opsForHash().get(email, code);

		return value != null && value.equals(TRUE);
	}

	public void updateEmailCode(String email, String code) {
		if (stringRedisTemplate.opsForHash().get(email, code) == null) {
			throw new EmailCodeNotFoundException();
		}

		stringRedisTemplate.opsForHash().put(email, code, TRUE);
	}

	public void deleteEmailCode(String email) {

		stringRedisTemplate.delete(email);

	}

}
