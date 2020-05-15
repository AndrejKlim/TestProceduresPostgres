package com.demo.testprocedurespostgres;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Sheduler {

	private final EmpRepo empRepo;

	public Sheduler(EmpRepo empRepo) {
		this.empRepo = empRepo;
	}

	@Scheduled(fixedRate = 10000)
	public void createUser(){
		//System.out.println(10);
		//empRepo.insert();
		System.out.println(empRepo.sumSalary());
	}
}
