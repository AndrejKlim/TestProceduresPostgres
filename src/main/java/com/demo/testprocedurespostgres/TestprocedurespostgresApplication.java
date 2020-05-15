package com.demo.testprocedurespostgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TestprocedurespostgresApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestprocedurespostgresApplication.class, args);
	}

}
