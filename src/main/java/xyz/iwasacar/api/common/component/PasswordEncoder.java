package xyz.iwasacar.api.common.component;

public interface PasswordEncoder {

	String encode(String rawPassword);

	boolean matches(String rawPassword, String encodedPassword);

}
