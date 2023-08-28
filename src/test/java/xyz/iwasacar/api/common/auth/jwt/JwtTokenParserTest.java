package xyz.iwasacar.api.common.auth.jwt;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import xyz.iwasacar.api.domain.roles.entity.RoleName;

class JwtTokenParserTest {

	private static final String secret = "secretsecretsecretsecretsecretsecretsecretsecretsecret";
	private static final long access = 3000L;
	private static final long refresh = 30000L;

	private JwtTokenProvider provider;
	private JwtTokenParser parser;

	@BeforeEach
	void setUp() {
		provider = new JwtTokenProvider(secret, access, refresh);
		parser = new JwtTokenParser(secret);
	}

	@DisplayName("토큰 생성 & 파싱")
	@Test
	void test() {
		Long memberId = 1L;
		List<RoleName> roles = List.of(RoleName.MEMBER, RoleName.ADMIN);

		MemberClaim memberClaim = new MemberClaim(memberId, roles);
		JwtDto jwtDto = provider.createJwt(memberClaim);
		String jwt = jwtDto.getAccessToken();

		MemberClaim memberClaims = parser.getMemberClaims(jwt);

		assertThat(memberClaims.getMemberId()).isEqualTo(memberId);
		assertThat(memberClaims.getRoles()).isEqualTo(roles);
	}

}
