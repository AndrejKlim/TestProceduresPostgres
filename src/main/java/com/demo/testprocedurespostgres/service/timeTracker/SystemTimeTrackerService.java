package com.demo.testprocedurespostgres.service.timeTracker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SystemTimeTrackerService implements TimeTracker{

	private VoidReturningMethod voidReturningMethod;

	@Override
	public long getTimeForMethodProcessing(VoidReturningMethod voidReturningMethod) {
		log.info("Starting processing method");
		long start = System.currentTimeMillis();
		voidReturningMethod.run();
		long fin = System.currentTimeMillis();
		long operationTime = fin-start;
		log.info("Time to process method - {} ms, {} s, {} min in thread {}",
				operationTime,
				TimeUnit.MILLISECONDS.toSeconds(operationTime),
				TimeUnit.MILLISECONDS.toMinutes(operationTime),
				Thread.currentThread().getName());
		return operationTime;
	}
}
