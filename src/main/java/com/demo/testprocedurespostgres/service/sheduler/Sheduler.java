package com.demo.testprocedurespostgres.service.sheduler;

import com.demo.testprocedurespostgres.repo.EmpRepo;
import com.demo.testprocedurespostgres.repo.SalePointRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Sheduler {

	@Autowired
	SalePointRepo salePointRepo;

	//@Async
//	@Scheduled(fixedRate = 10000)
	public void createUser(){
		//System.out.println(10);
		//empRepo.insert();
		long start  = System.currentTimeMillis();
		log.info("start calculating");
		salePointRepo.proxyCalcPrize();
		long fin = System.currentTimeMillis();
		log.info("Time to process procedure - {} ms, {} s, {} min in thread {}",
				(fin-start), (fin-start)/1000, (fin-start)/1000/60,
				Thread.currentThread().getName());
	}
}
