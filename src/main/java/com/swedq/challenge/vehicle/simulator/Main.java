/**
 * @author Ziaa<github.com/ziaagikian>
 */

package com.swedq.challenge.vehicle.simulator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.swedq.challenge.vehicle.simulator.models.ConfigResponse;
import com.swedq.challenge.vehicle.simulator.models.PingResponse;
import com.swedq.challenge.vehicle.simulator.utils.ApiUtils;
import com.swedq.challenge.vehicle.simulator.utils.Constants;
import com.swedq.challenge.vehicle.simulator.utils.Helpers;

public class Main {

	// Backend APIs Paths
	private static final String API_PING = "/api/v1/vehicles/ping";
	private static final String API_VEHICLE_CONFIG = "/api/v1/vehicles/configs";

	private static ApiUtils sApiUtils;
	// Default Simulation Interval 10 Minutes
	private static int sSimulationDuration ;
	// Counter for number of retries
	private static int sRerty;

	public static void main(String[] args) throws Exception {

		// Check first argument and ignore others.
		if (args.length > 0) {
			int inputSimlDuration = Helpers.parseSimulationCmdArg(args[0]);
			sSimulationDuration = inputSimlDuration > 0 ? inputSimlDuration : Constants.DEFAULT_SIMULATION_DURATION;
		} else {
			System.out.println(
					"Input time is not given the simulation will run for  default " + (Constants.DEFAULT_SIMULATION_DURATION/60) + " minutes");
		}

		sApiUtils = new ApiUtils();

		ConfigResponse vehicleConfigs = getVehicleConfigs();
		if (vehicleConfigs != null && vehicleConfigs.isSuccess() && vehicleConfigs.getEntities().size() > 0) {
			ArrayList<Integer> ids = vehicleConfigs.getEntities();
			System.out.println("Simulator is conncted with server.");
			if (ids.size() > 0)
				startTestSimulation(ids);
			else
				throw new Exception("Vehicle are not configured in backend pls contact @{author}");
		} else
			throw new Exception("Cannot Execute Simulation. Check backend Connectivity or contact @{author}");
	}

	/**
	 * Start simulation for the given Car Ids
	 * @param idsList Car Ids
	 */
	private static void startTestSimulation(ArrayList<Integer> idsList) {

		System.out.println("****** Starting Simulation ****  ");
		// Single threaded model
		ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		executorService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				Iterator<Integer> iterVehList = idsList.iterator();
				while (iterVehList.hasNext()) {
					int vehicleID = iterVehList.next();

					// Simulate Vehicle disconnectivity
					if (Helpers.RandomFloat() > Constants.DISCONNECTIVITY_TRESHOLD)
						pingVehicle(vehicleID);
					else {
						// Vehicle is offline
						System.err.println("Vehile number " + vehicleID + " is offline");
					}
				}
			}
		}, Constants.SIMULATOR_INITIAL_DELAY, Constants.SIMULATOR_BATCH_INTERVAL, TimeUnit.SECONDS);

		// Running simulation for Specific time
		try {
			executorService.awaitTermination(sSimulationDuration, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Shutdown thread safely after execution
		executorService.shutdownNow();
		System.out.println("**** Successfully executed simulation ******");
	}

	/**
	 * Getting Configuration of registered vehicles. This method can also be for
	 * checking backend connectivity.
	 * 
	 * @return {ConfigResponse}
	 */
	private static ConfigResponse getVehicleConfigs() {
		ConfigResponse configResponse = sApiUtils.httpGet(API_VEHICLE_CONFIG, ConfigResponse.class);
		if (configResponse == null || !configResponse.isSuccess()
				|| configResponse.getStatusCode() != Constants.HTTP_OK) {

			System.err.println("System not connected with server .... trying to connect  ");
			System.err.println((Constants.MAX_SERVER_RETRIES - sRerty) + "seconds left");

			// Server connection Retries
			try {
				sRerty++;
				if (sRerty < Constants.MAX_SERVER_RETRIES) {
					Thread.sleep(1 * 1000); // Sleep for 1 second
					getVehicleConfigs();
				} else {
					throw new Exception("Cannot executing Simulation due to server error.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return configResponse;
	}

	/**
	 * Heartbeat service between vehicle and server. This method is used to invoke
	 * PING API.
	 * 
	 * @param id  => vehicleID
	 */
	private static void pingVehicle(int id) {
		// Body Parameter
		List<NameValuePair> bodyParams = new ArrayList<>();
		bodyParams.add(new BasicNameValuePair("id", id + ""));

		PingResponse pingResponse = sApiUtils.httpPost(API_PING, bodyParams, PingResponse.class);

		if (pingResponse == null || !pingResponse.isSuccess() || pingResponse.getStatusCode() != Constants.HTTP_OK) {
			System.err.println("PING  API  not responding for vehicle id " + id);
		} else {
			System.out.println("ACK  for Vehicle VIN. " + pingResponse.getEntities().get(0).getVin() + " recieved.");
		}
	}

}
