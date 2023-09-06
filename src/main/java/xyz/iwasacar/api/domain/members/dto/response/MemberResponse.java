package xyz.iwasacar.api.domain.members.dto.response;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.domain.members.entity.Gender;
import xyz.iwasacar.api.domain.roles.entity.RoleName;

@Builder
@RequiredArgsConstructor
@Getter
public class MemberResponse implements Serializable {

	private static final long serialVersionUID = 2920753163261664224L;

	private final Long id;
	private final String email;

	private final String name;

	private final String tel;

	private final Gender gender;

	private final Boolean hasLicense;

	private final List<RoleName> roles;

}
