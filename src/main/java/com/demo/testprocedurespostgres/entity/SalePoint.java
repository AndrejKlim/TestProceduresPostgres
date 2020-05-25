package com.demo.testprocedurespostgres.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class SalePoint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String pointName;
	@Column
	private String place;
	@Column
	private int soldSim;
	@Column
	private int soldModem;
	@Column
	private int soldTvAdapters;


	public SalePoint(String pointName, String place, int soldSim, int soldModem, int soldTvAdapters) {
		this.pointName = pointName;
		this.place = place;
		this.soldSim = soldSim;
		this.soldModem = soldModem;
		this.soldTvAdapters = soldTvAdapters;
	}
}
