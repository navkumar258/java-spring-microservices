package com.example.account.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppProperties {

	@Value("${rabbitmq.user.exchange.name}")
	private String exchangeName;

	@Value("${rabbitmq.user.creation.routing.key}")
	private String userCreationRoutingKey;

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public String getUserCreationRoutingKey() {
		return userCreationRoutingKey;
	}

	public void setUserCreationRoutingKey(String userCreationRoutingKey) {
		this.userCreationRoutingKey = userCreationRoutingKey;
	}
}
