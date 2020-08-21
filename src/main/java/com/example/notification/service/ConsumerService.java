package com.example.notification.service;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.notification.dto.User;

@Service
public class ConsumerService {

	private static final Logger log = LoggerFactory.getLogger(ConsumerService.class);

	private final EmailService emailService;

	public ConsumerService(EmailService emailService) {
		this.emailService = emailService;
	}

	@RabbitListener(queues = "${rabbitmq.user.creation.queue.name}")
	public void receive(final User message) {
		log.info("Received message " + message.toString());
		try {
			emailService.setMailDetailsForSend(message.toString(), "abcd@mail.com");
		} catch (MessagingException e) {
			log.error("Error in sending user welcome mail - " + e.getMessage());
		}
	}
}
