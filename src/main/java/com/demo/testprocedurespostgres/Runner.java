package com.demo.testprocedurespostgres;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Runner implements CommandLineRunner {

	@Autowired
	EmpRepo empRepo;

	@Override
	public void run(String... args) throws Exception {
//		int i = 100000;
//		while (i<=100000000){
//			empRepo.save(new Emp("Name", "Role", i));
//			i++;
//		}
		//empRepo.insert();
		//empRepo.save(new Emp("asda", "adwadaw", 20));
		System.out.println(empRepo.getEmpsCountBySalaryGreaterThen(1000));
	}
}
