package com.demo.testprocedurespostgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpRepo extends JpaRepository<Emp, Long> {

//	@Procedure("getEmpsCountBySalaryGreaterThen")
//	int getEmpsCountBySalaryGreaterThen(int salary);

	@Procedure(name = "Emp.getEmpsCountBySalaryGreaterThen")
	int getEmpsCountBySalaryGreaterThen(@Param("salary_in") int salary_in);

	@Procedure("createUser")
	void insert();

	@Procedure(value = "sumSalary")
	long sumSalary();
}
