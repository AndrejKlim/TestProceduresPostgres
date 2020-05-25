package com.demo.testprocedurespostgres.repo;

import com.demo.testprocedurespostgres.entity.CalcResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalcResultRepo  extends JpaRepository<CalcResult, Long> {
}
