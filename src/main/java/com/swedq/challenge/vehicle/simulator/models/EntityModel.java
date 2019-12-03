package com.swedq.challenge.vehicle.simulator.models;

/**
 * Response Entity POJO
 * @author ziaa
 *
 */
public class EntityModel {

	private int id;
	private String vin;
	private String regNum;
	private String status;
	private String lastUpdated;
	private int userId;

	public Integer getId() {
	return id;
	}


	public String getVin() {
	return vin;
	}


	public String getRegNum() {
	return regNum;
	}

	public String getStatus() {
	return status;
	}


	public String getLastUpdated() {
	return lastUpdated;
	}


	public Integer getUserId() {
	return userId;
	}
}
