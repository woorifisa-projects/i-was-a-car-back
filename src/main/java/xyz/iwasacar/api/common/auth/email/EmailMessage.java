package xyz.iwasacar.api.common.auth.email;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@Getter
public class EmailMessage {

	private final String to;

	private final String subject;

	private final String message;
}
