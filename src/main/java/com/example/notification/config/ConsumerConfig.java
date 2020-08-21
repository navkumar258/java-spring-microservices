package com.example.notification.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {

	private final AppProperties appProperties;

	public ConsumerConfig(AppProperties appProperties) {
		this.appProperties = appProperties;
	}

	@Bean
	public TopicExchange eventExchange() {
		return new TopicExchange(appProperties.getExchangeName());
	}

	@Bean
	public Queue queue() {
		return new Queue(appProperties.getUserCreationQueueName(), true);
	}

	@Bean
	public Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(appProperties.getUserCreationRoutingKey());
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}
