package com.demo.testprocedurespostgres.repo;

import com.demo.testprocedurespostgres.entity.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpRepo extends JpaRepository<Emp, Long> {

	@Procedure("getEmpsCountBySalaryGreaterThen")
	int getEmpsCountBySalaryGreaterThen(int salary);

//	@Procedure(name = "Emp.getEmpsCountBySalaryGreaterThen")
//	int getEmpsCountBySalaryGreaterThen(@Param("salary_in") int salary_in);

	@Procedure("createUser")
	void insert(int i);

	@Procedure(value = "sumSalary")
	long sumSalary();
}
