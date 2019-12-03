package com.swedq.challenge.vehicle.simulator.utils;

/**
 * Global Constants/Configs
 * @author ziaa
 *
 */
public class Constants {
	
	// CommandLine Arguments
	public static final  String INPUT_REGEX = "\\d+[H M S]$"; 
//	public static final  String INPUT_REGEX ="^[1-9][ H M S ]$"; 
	// API Path
	public  static  final String API_PATH = "http://localhost:8080";
	public static final int HTTP_OK = 200;
	public static final int  MAX_SERVER_RETRIES = 5; //MAXIMUM tries for Config APIs
	
	public static final String  TEST_JSON_TOKEN = 
			"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoidGVzdCIsImlhdCI6MTU3NDM0OTYzNiwiZXhwIjoxNjA1OTA3MjM2fQ.q13N2siftymEvCl8RNXXYkqzuASbm5bYvaoF1jIMV-w"; 
	public static final float DISCONNECTIVITY_TRESHOLD = 0.1f; // 10 %
	
	public static int DEFAULT_SIMULATION_DURATION = 10 * 60 ; // 10 Minutes
	public static final  int SIMULATOR_INITIAL_DELAY = 3; // 3  seconds
	public static final  long SIMULATOR_BATCH_INTERVAL = 30 ; // 30 second
	
}
