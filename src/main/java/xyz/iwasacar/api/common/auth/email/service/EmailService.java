package xyz.iwasacar.api.common.auth.email.service;

import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import xyz.iwasacar.api.common.auth.email.EmailMessage;
import xyz.iwasacar.api.common.auth.email.EmailSession;
import xyz.iwasacar.api.common.auth.email.exception.EmailServerException;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender javaMailSender;

	private final SpringTemplateEngine templateEngine;

	private final EmailSession redisUtil;

	public String sendMail(final EmailMessage emailMessage) {

		String authNum = createCode();

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
			mimeMessageHelper.setTo(emailMessage.getTo()); // 메일 수신자
			mimeMessageHelper.setSubject(emailMessage.getSubject()); // 메일 제목
			mimeMessageHelper.setText(setContext(authNum), true); // 메일 본문 내용, HTML 여부
			javaMailSender.send(mimeMessage);

			log.info("이메일 전송 성공");

			redisUtil.setEmailCode(emailMessage.getTo(), authNum);

			return authNum;

		} catch (MessagingException e) {
			log.info("이메일 전송 실패");
			throw new EmailServerException();
		}
	}

	// 인증번호 생성 메서드
	private String createCode() {
		return UUID.randomUUID().toString().split("-")[0].substring(0, 6);
	}

	// thymeleaf를 통한 html 적용
	public String setContext(final String code) {

		Context context = new Context();
		context.setVariable("code", code);
		return templateEngine.process("email", context);
	}

	public void confirmEmailCode(final String email, final String code) {

		redisUtil.updateEmailCode(email, code);
	}

}
