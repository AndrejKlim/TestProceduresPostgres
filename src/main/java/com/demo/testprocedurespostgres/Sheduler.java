package com.demo.testprocedurespostgres;

import com.demo.testprocedurespostgres.repo.EmpRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Sheduler {

	private final EmpRepo empRepo;

	public Sheduler(EmpRepo empRepo) {
		this.empRepo = empRepo;
	}

	//@Async
	//@Scheduled(fixedRate = 200)
	public void createUser(){
		//System.out.println(10);
		//empRepo.insert();
//		long start  = System.currentTimeMillis();
//		log.info("Sum = {}",empRepo.sumSalary());
//		long fin = System.currentTimeMillis();
//		log.info("Time to process procedure - {} ms, in thread {}",
//				(fin-start),
//				Thread.currentThread().getName());
	}
}
