package xyz.iwasacar.api.common.component;

import org.mindrot.jbcrypt.BCrypt;

public class BcryptPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(String rawPassword) {
		return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
	}

	@Override
	public boolean matches(String rawPassword, String encodedPassword) {
		return BCrypt.checkpw(rawPassword, encodedPassword);
	}

}
