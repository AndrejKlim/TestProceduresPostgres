package com.demo.testprocedurespostgres.service;

import com.demo.testprocedurespostgres.repo.SalePointRepo;
import com.demo.testprocedurespostgres.service.notifier.NoticePublisher;
import com.demo.testprocedurespostgres.service.notifier.Notifier;
import com.demo.testprocedurespostgres.service.timeTracker.TimeTracker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MainService implements CommandLineRunner {

	@Autowired
	NoticePublisher noticePublisher;
	@Qualifier("activeMQNotifierService")
	@Autowired
	private Notifier activeMQNotifier;
	@Qualifier("emailNotifierService")
	@Autowired
	private Notifier emailNotifier;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private TimeTracker timeTracker;
	@Autowired
	private SalePointRepo salePointRepo;

	@Override
	public void run(String... args) {

		noticePublisher.subscribe(emailNotifier);
		noticePublisher.subscribe(activeMQNotifier);

		noticePublisher.notifySubscribers("Procedure start processing db");
		log.info("Truncating prize db");
		jdbcTemplate.execute("truncate \"testTaskDB\".public.sale_point_prize;");
		timeTracker.getTimeForMethodProcessing(salePointRepo::proxyAlterCalcPrize);
		noticePublisher.notifySubscribers("Procedure finished processing db");
	}
}
