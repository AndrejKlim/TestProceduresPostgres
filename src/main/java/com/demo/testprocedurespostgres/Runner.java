package com.demo.testprocedurespostgres;

import com.demo.testprocedurespostgres.entity.SalePoint;
import com.demo.testprocedurespostgres.repo.EmpRepo;
import com.demo.testprocedurespostgres.repo.SalePointRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Random;

@Service
@Slf4j
public class Runner implements CommandLineRunner {

	@Autowired
	EmpRepo empRepo;
	@Autowired
	SalePointRepo salePointRepo;
	@Autowired
	EntityManager entityManager;

	@Override
	public void run(String... args) throws Exception {
		final Random random = new Random();
		int i = 0;
		while (i<8000){
			ArrayList<SalePoint> salePoints = new ArrayList<>();
			int j = 0;
			while (j<1000) {
				salePoints.add(getSalePoint(random));
				j++;
			}
			i++;
			salePointRepo.saveAll(salePoints);
		}
		//salePointRepo.createPrize(2,20,100);
//		int i = 100000000;
//		while (i<=1000000000){
//			ArrayList<Emp> emps = new ArrayList<>();
//			for (int j=0; j<1000; j++) {
//				emps.add(new Emp("Name", "Role", i+j));
//				//empRepo.save(new Emp("Name", "Role", i));
//			}
//			empRepo.saveAll(emps);
//			i+=3333;
		//}
		//empRepo.insert();
		//empRepo.save(new Emp("asda", "adwadaw", 20));
		//System.out.println(empRepo.getEmpsCountBySalaryGreaterThen(1000));

	}

	private SalePoint getSalePoint(Random random){

		String pointName = "PointName";
		String place = "Place";
		int soldSim;
		int soldModem;
		int soldTvAdapters;

		soldSim = random.nextInt(5000);
		soldModem = random.nextInt(2000);
		soldTvAdapters = random.nextInt(1000);
		return new SalePoint(pointName, place, soldSim, soldModem, soldTvAdapters);
	}
}
