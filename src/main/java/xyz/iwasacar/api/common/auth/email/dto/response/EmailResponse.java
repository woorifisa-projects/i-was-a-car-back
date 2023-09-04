package xyz.iwasacar.api.common.auth.email.dto.response;

import lombok.Getter;

@Getter
public class EmailResponse {
	private String code;

	public void setCode(String code) {
		this.code = code;
	}
}
