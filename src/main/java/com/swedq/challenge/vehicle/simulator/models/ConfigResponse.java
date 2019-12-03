package com.swedq.challenge.vehicle.simulator.models;

import java.util.ArrayList;

/**
 * Config API response POJO
 * @author ziaa
 *
 */
public class ConfigResponse {
	
	private boolean success;
	private int statusCode;
	private String message;
	private ArrayList<Integer> entities = new ArrayList<>();
	
	public boolean isSuccess() {
		return success;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public String getMessage() {
		return message;
	}
	public ArrayList<Integer> getEntities() {
		return entities;
	}
	
}
