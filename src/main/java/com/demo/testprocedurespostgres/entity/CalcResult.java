package com.demo.testprocedurespostgres.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CalcResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private int recordsNumber;
	@Column
	private long operationTime;


	public CalcResult(int recordsNumber, long operationTime) {
		this.recordsNumber = recordsNumber;
		this.operationTime = operationTime;
	}
}
