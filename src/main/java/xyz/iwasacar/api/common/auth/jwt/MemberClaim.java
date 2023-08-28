package xyz.iwasacar.api.common.auth.jwt;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.roles.entity.RoleName;

@RequiredArgsConstructor
@Getter
public class MemberClaim {

	private final Long memberId;
	private final List<RoleName> roles;

}
