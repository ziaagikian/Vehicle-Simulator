package com.swedq.challenge.vehicle.simulator.models;

import java.util.ArrayList;
import java.util.List;

/**
 * PING API response POJO
 * @author ziaa
 *
 */
public class PingResponse {
	
	private boolean success;
	private int statusCode;
	private String message;
	private List<EntityModel> entities = new ArrayList<>();
	
	public int getStatusCode() {
	return statusCode;
	}

	public String getMessage() {
	return message;
	}

	public List<EntityModel> getEntities() {
	return entities;
	}

	public boolean isSuccess() {
		return success;
	}

}
