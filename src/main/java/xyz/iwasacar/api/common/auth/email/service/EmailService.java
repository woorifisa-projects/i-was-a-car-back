package xyz.iwasacar.api.common.auth.email.service;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import xyz.iwasacar.api.common.auth.email.EmailMessage;
import xyz.iwasacar.api.common.auth.email.EmailSession;
import xyz.iwasacar.api.common.auth.email.exception.EmailServerException;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender javaMailSender;

	private final SpringTemplateEngine templateEngine;

	private final EmailSession redisUtil;

	public String sendMail(EmailMessage emailMessage) {

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
		Random random = new Random();
		StringBuffer key = new StringBuffer();

		for (int i = 0; i < 5; i++) {
			int index = random.nextInt(4);

			switch (index) {
				case 0:
					key.append((char)((int)random.nextInt(26) + 97));
					break;
				case 1:
					key.append((char)((int)random.nextInt(26) + 65));
					break;
				default:
					key.append(random.nextInt(9));
			}
		}
		return key.toString();
	}

	// thymeleaf를 통한 html 적용
	public String setContext(String code) {
		Context context = new Context();
		context.setVariable("code", code);
		return templateEngine.process("email", context);
	}

	public void confirmEmailCode(String email, String code) {

		redisUtil.updateEmailCode(email, code);

	}

}
