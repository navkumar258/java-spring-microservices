package com.example.notification.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	private final JavaMailSender javaMailSender;

	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}


	public void setMailDetailsForSend(final String payload, final String email) throws MessagingException {
		final MimeMessage mail = javaMailSender.createMimeMessage();
		final MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		helper.setTo(email);
		helper.setSubject("Notification");
		helper.setText("text/html", payload);
		javaMailSender.send(mail);

	}
}
