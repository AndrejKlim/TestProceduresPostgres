package com.demo.testprocedurespostgres;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedStoredProcedureQuery(
		name = "Emp.getEmpsCountBySalaryGreaterThen",
		procedureName = "getEmpsCountBySalaryGreaterThen",
		parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "salary_in", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.OUT, name = "count_out", type = Integer.class)
})
public class Emp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private String role;
	@Column
	private int salary;

	public Emp(String name, String role, int salary) {
		this.name = name;
		this.role = role;
		this.salary = salary;
	}
}
