package com.demo.testprocedurespostgres.service.notifier.email;

import com.demo.testprocedurespostgres.service.notifier.Notifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailNotifierService implements Notifier {

	@Autowired
	JavaMailSender javaMailSender;

	String subject = "Processing Procedure";
	String mailTo = "tydysch@mail.ru";

	@Override
	public boolean sendNotice(String notice) {
		SimpleMailMessage message = new SimpleMailMessage();

		if (canSend()) {
			message.setTo(mailTo);
			message.setSubject(subject);
			message.setText(notice);
			javaMailSender.send(message);
			log.info("Notice sending successfully, notice : {}", notice);
			subject = null;
			mailTo = null;
			return true;
		}else{
			return false;
		}
	}

	boolean canSend(){
		return mailTo != null && subject != null;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
}
