package com.demo.testprocedurespostgres.service.notifier;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticePublisher {

	List<Notifier> subscribers = new ArrayList<>();

	public void subscribe(Notifier notifier){
		subscribers.add(notifier);
	}

	public void unsubscribe(Notifier notifier){
		subscribers.remove(notifier);
	}

	public void notifySubscribers(String notice){
		subscribers.forEach(s -> s.sendNotice(notice));
	}
}
