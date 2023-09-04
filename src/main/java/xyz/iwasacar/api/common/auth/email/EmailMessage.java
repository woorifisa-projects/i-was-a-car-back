package xyz.iwasacar.api.common.auth.email;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmailMessage {

	private String to;

	private String subject;

	private String message;
}
