package com.demo.testprocedurespostgres;

import com.demo.testprocedurespostgres.entity.CalcResult;
import com.demo.testprocedurespostgres.entity.SalePoint;
import com.demo.testprocedurespostgres.repo.CalcResultRepo;
import com.demo.testprocedurespostgres.repo.EmpRepo;
import com.demo.testprocedurespostgres.repo.SalePointRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class Runner implements CommandLineRunner {

	@Autowired
	SalePointRepo salePointRepo;
	@Autowired
	CalcResultRepo calcResultRepo;
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... args) throws Exception {
		final Random random = new Random();
//		int [] dataRecords = {0, 10000, 50000, 100000, 500000, 1000000, 2000000, 3000000,
//								5000000, 8000000, 10000000, 12000000, 15000000, 17000000, 20000000};
//
//		log.info("Starting processing procedure");
//		long start = System.currentTimeMillis();
//		salePointRepo.proxyCalcPrize();
//		long fin = System.currentTimeMillis();
//		long operationTime = fin-start;
//		log.info("Time to process procedure - {} ms, {} s, {} min in thread {}",
//				operationTime,
//				TimeUnit.MILLISECONDS.toSeconds(operationTime),
//				TimeUnit.MILLISECONDS.toMinutes(operationTime),
//				Thread.currentThread().getName());
//		log.info("Save result to DB, records = {}, time = {} ms", 10000000, operationTime);
//		calcResultRepo.save(new CalcResult(10000000, operationTime));

		int[] dataRecords = {0, 10000, 50000, 100000, 500000, 1000000,
				5000000, 10000000, 20000000};
		int counter = 0;
		int i = 1;
		while (i <= dataRecords.length-1){
			int cursor = dataRecords[i];
			ArrayList<SalePoint> list ;
			log.info("Start generating records, up to {}", cursor);
			for (int j = dataRecords[i-1]/1000; j < cursor/1000; j++){
				list = new ArrayList<>();
				for (int k = 0; k < 1000; k++){
					list.add(getSalePoint(random));
				}
				salePointRepo.saveAll(list);
				//list.add(getSalePoint(random));
			}
			//salePointRepo.saveAll(list);
			log.info("Now {} records int DB", cursor);
			log.info("Truncating prize db");
			jdbcTemplate.execute("truncate \"testTaskDB\".public.sale_point_prize;");
			log.info("Starting processing procedure");
			long start = System.currentTimeMillis();
			salePointRepo.proxyAlterCalcPrize();
			long fin = System.currentTimeMillis();
			long operationTime = fin-start;
			log.info("Time to process procedure - {} ms, {} s, {} min in thread {}",
					operationTime,
					TimeUnit.MILLISECONDS.toSeconds(operationTime),
					TimeUnit.MILLISECONDS.toMinutes(operationTime),
					Thread.currentThread().getName());
			log.info("Save result to DB, records = {}, time = {} ms", cursor, operationTime
			);
			calcResultRepo.save(new CalcResult(cursor, operationTime));
			i++;
		}
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
