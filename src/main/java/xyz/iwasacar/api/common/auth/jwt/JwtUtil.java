package xyz.iwasacar.api.common.auth.jwt;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtil {

	public static final String AUTHORIZATION_ID = "AUTHORIZATION_ID";
	public static final String ROLES = "ROLES";

	public static final String SUBJECT = "IWASACAR";
	public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
	public static final String REFRESH_TOKEN = "REFRESH_TOKEN";

	public static final String AUTH_INFO = "AUTH_INFO";

}
