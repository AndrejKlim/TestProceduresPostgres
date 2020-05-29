package com.demo.testprocedurespostgres.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SimpleMessageListenerContainer;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

@Configuration
public class ActiveMQConfig {

	@Value("${app.jms.notice.queue}")
	private String noticeQueue;

	@Qualifier("jmsConnectionFactory")
	@Autowired
	ConnectionFactory connectionFactory;


	@Bean
	public Destination noticeQueue(){
		return new ActiveMQQueue(noticeQueue);
	}

	@Bean
	public JmsTemplate jmsTemplate(){
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory);
		jmsTemplate.setDefaultDestination(noticeQueue());
		return jmsTemplate;
	}
}
