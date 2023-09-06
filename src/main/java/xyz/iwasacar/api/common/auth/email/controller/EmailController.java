package xyz.iwasacar.api.common.auth.email.controller;

import static org.springframework.http.HttpStatus.*;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.iwasacar.api.common.auth.email.EmailMessage;
import xyz.iwasacar.api.common.auth.email.dto.request.EmailConfirmRequest;
import xyz.iwasacar.api.common.auth.email.dto.request.EmailRequest;
import xyz.iwasacar.api.common.auth.email.dto.response.EmailResponse;
import xyz.iwasacar.api.common.auth.email.service.EmailService;
import xyz.iwasacar.api.common.dto.response.CommonResponse;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class EmailController {

	private final EmailService emailService;

	@PostMapping("/email")
	public ResponseEntity<CommonResponse<EmailResponse>> sendJoinMail(@Valid @RequestBody final EmailRequest request) {

		EmailMessage emailMessage = EmailMessage.builder()
			.to(request.getEmail())
			.subject("[I.WAS.A.CAR] 이메일 인증을 위한 인증 코드 발송")
			.build();

		String code = emailService.sendMail(emailMessage);

		EmailResponse emailResponse = new EmailResponse(code);

		return CommonResponse.success(OK, OK.value(), emailResponse);
	}

	@PostMapping("/email-confirm")
	public ResponseEntity<CommonResponse<Void>> confirmEmailCode(
		@Valid @RequestBody final EmailConfirmRequest request) {

		emailService.confirmEmailCode(request.getEmail(), request.getCode());

		return CommonResponse.success(OK, OK.value(), null);
	}

}
