package com.demo.testprocedurespostgres.repo;

import com.demo.testprocedurespostgres.entity.SalePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

public interface SalePointRepo extends JpaRepository<SalePoint, Long> {

	@Procedure("calcPrize")
	void calculatePrize();

	@Procedure("proxyCalcPrize")
	int proxyCalcPrize();

	@Procedure("proxyAlterCalcPrize")
	void proxyAlterCalcPrize();
}
