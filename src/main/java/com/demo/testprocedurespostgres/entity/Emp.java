package com.demo.testprocedurespostgres.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
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
