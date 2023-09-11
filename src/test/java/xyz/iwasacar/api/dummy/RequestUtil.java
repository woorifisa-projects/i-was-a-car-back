package xyz.iwasacar.api.dummy;

import static xyz.iwasacar.api.common.auth.jwt.JwtUtil.*;

import javax.servlet.http.Cookie;

public class RequestUtil {

	public static Cookie getCookie(String jwt, long age) {
		Cookie cookie = new Cookie(ACCESS_TOKEN, jwt);
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		cookie.setSecure(false);
		cookie.setMaxAge((int)(age / 1000));

		return cookie;
	}

}
