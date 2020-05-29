package com.demo.testprocedurespostgres.service.notifier.activemq;

import com.demo.testprocedurespostgres.service.notifier.Notifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service
@Slf4j
public class ActiveMQNotifierService implements Notifier {

	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private Destination noticeQueue;

	@Override
	public boolean sendNotice(String notice) {
		jmsTemplate.send(noticeQueue, session -> session.createTextMessage(notice));
		log.info("Notice sending successfully, notice : {}", notice);
		return true;
	}
}
