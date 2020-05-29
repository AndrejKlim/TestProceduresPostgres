package com.demo.testprocedurespostgres.service;

import com.demo.testprocedurespostgres.repo.SalePointRepo;
import com.demo.testprocedurespostgres.service.notifier.Notifier;
import com.demo.testprocedurespostgres.service.timeTracker.TimeTracker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MainService implements CommandLineRunner {

	@Autowired
	SalePointRepo salePointRepo;
	@Autowired
	Notifier notifier;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	TimeTracker timeTracker;


	@Override
	public void run(String... args) {
		notifier.sendNotice("Procedure start processing db", "Start Processing", "tydysch@mail.ru");
		log.info("Truncating prize db");
		jdbcTemplate.execute("truncate \"testTaskDB\".public.sale_point_prize;");
		timeTracker.getTimeForMethodProcessing(salePointRepo::proxyAlterCalcPrize);
		notifier.sendNotice("Procedure finished processing db", "Finish processing", "tydysch@mail.ru");
	}
}
