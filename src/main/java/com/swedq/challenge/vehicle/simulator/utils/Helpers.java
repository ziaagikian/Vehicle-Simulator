
package com.swedq.challenge.vehicle.simulator.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Common Helper Class
 * 
 * @author ziaa
 */
public class Helpers {

	// Generate Float Number between 0.0 and 1.0
	public static float RandomFloat() {
		Random rand = new Random();
		return rand.nextFloat();
	}

	/**
	 * Parse incoming Input parameter. User can provide input in Hours[H | h],
	 * Minutes [M | m] or Seconds [S|s]. The suffix can be provided explicitly.
	 * Otherwise the paring will not execute and Simulation will last for default 10
	 * minutes.
	 * 
	 * @param sel Incoming string
	 * @return Intervals in seconds.
	 */
	public static int parseSimulationCmdArg(String sel) {
		Pattern pattern = Pattern.compile(Constants.INPUT_REGEX, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(sel);

		int interval = 0;
		if (matcher.matches()) {
			String intString = sel.substring(0, sel.length() - 1);
			String intervalType = sel.substring(sel.length() - 1, sel.length());
			int input = Integer.parseInt(intString);
			interval = applyFormualae(input, intervalType);
		}else {
			showErrorMessage();
		}
		return interval;
	}

	private static void showErrorMessage() {
		System.err.println("0 or Wrong input format is given the simulation and it will run for default " +
				(Constants.DEFAULT_SIMULATION_DURATION/60) + " minutes");
	}

	/**
	 * Change incoming values to <b>seconds </b>
	 * 
	 * @param input Input
	 * @param intervalType Valid suffix lateral
	 * 
	 * @return Calculated value or Zero
	 */
	public static int applyFormualae(int input, String intervalType) {
		int calculatedVal = 0;
		if (input > 0) {
			switch (intervalType) {

			case "M":
			case "m":
				calculatedVal = input * 60;
				break;

			case "H":
			case "h":
				calculatedVal = input * 60;
				break;

			case "S":
			case "s":
				calculatedVal = input;
				break;
				
			default:
				calculatedVal = 0;
				break;

			}

		} else {
			showErrorMessage();
		}
		return calculatedVal;
	}
}
