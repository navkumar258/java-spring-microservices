package com.example.notification.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppProperties {

	@Value("${rabbitmq.user.exchange.name}")
	private String exchangeName;
	
	@Value("${rabbitmq.user.creation.routing.key}")
	private String userCreationRoutingKey;

	@Value("${rabbitmq.user.creation.queue.name}")
	private String userCreationQueueName;

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public String getUserCreationQueueName() {
		return userCreationQueueName;
	}

	public void setUserCreationQueueName(String userCreationQueueName) {
		this.userCreationQueueName = userCreationQueueName;
	}

	public String getUserCreationRoutingKey() {
		return userCreationRoutingKey;
	}

	public void setUserCreationRoutingKey(String userCreationRoutingKey) {
		this.userCreationRoutingKey = userCreationRoutingKey;
	}
}
