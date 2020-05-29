package com.demo.testprocedurespostgres.service.recordGenerator;

import com.demo.testprocedurespostgres.entity.SalePoint;
import com.demo.testprocedurespostgres.repo.SalePointRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
@Slf4j
public class RecordGeneratorService {

	@Autowired
	SalePointRepo salePointRepo;

	Random random = new Random();

	public void generateRecords(int numberOfRecords) {
		int i = 0;
		ArrayList<SalePoint> list = new ArrayList<>();
		log.info("Start generating {} records", numberOfRecords);
		while (i < numberOfRecords) {
			list.add(getSalePoint());
			if (i % 1000 == 0){
				salePointRepo.saveAll(list);
				list = new ArrayList<>();
			}
			i++;
		}
		log.info("Added {} records into DB", numberOfRecords);
	}

	private SalePoint getSalePoint(){

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
