package com.demo.testprocedurespostgres.repo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AsyncRunner {

	@Autowired
	SalePointRepo salePointRepo;

	@Async
	public CompletableFuture<Long> runDBProcedure(int recordQuarter){
		log.info("Starting processing procedure");
		long start = System.currentTimeMillis();
		salePointRepo.proxyAlterCalcPrize(recordQuarter);
		long fin = System.currentTimeMillis();
		long operationTime = fin-start;
		log.info("Time to process procedure - {} ms, {} s, {} min in thread {}",
				operationTime,
				TimeUnit.MILLISECONDS.toSeconds(operationTime),
				TimeUnit.MILLISECONDS.toMinutes(operationTime),
				Thread.currentThread().getName());
		return CompletableFuture.completedFuture(operationTime);
	}
}
