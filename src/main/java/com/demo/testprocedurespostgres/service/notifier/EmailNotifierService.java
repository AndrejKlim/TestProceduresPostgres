package com.demo.testprocedurespostgres.service.notifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotifierService implements Notifier{

	@Autowired
	JavaMailSender javaMailSender;

	String subject;
	String mailTo;

	@Override
	public boolean sendNotice(String notice, String ... args) {
		if (args.length != 0){
			subject = args[0];
			mailTo = args[1];
		}

		SimpleMailMessage message = new SimpleMailMessage();

		if (canSend()) {
			message.setTo(mailTo);
			message.setSubject(subject);
			message.setText(notice);
			javaMailSender.send(message);
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
