/**
 * 
 */
package com.swedq.challenge.vehicle.simulator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.swedq.challenge.vehicle.simulator.utils.Helpers;

/**
 * @author ziaa
 *
 */
public class HelperTest {
	
	private String mSecondscmdArg1 ;
	private String mSecondcmdArg2;
	private String mMinutescmdArg1;
	private String mMinutescmdArg2;
	private String mHourscmdArg1;
	private String mHourscmdArg2;
	private String mZeroCmdArg;
	private String mInvalidCmdArg;
	
	private String mSecondsArgs1;
	private String mSecondsArgs2;
	private String mMinutesArgs1;
	private String mMinutesArgs2;
	private String mHoursArgs1;
	private String mHoursArgs2;
	private String mInvalidArgs1;
	private String mInvalidArgs2;
	
	private int mMaxSampleInteger;
//	private int mMinSampleInteger;
	private int mZeroVal ;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		 mSecondscmdArg1 = "10S";
		 mSecondcmdArg2 = "10s";
		 mMinutescmdArg1 = "10M";
		 mMinutescmdArg2 = "10m";
		 mHourscmdArg1 = "10H";
		 mHourscmdArg2 = "10h";
		 mZeroCmdArg = "0";
		 mInvalidCmdArg = "7878AQQ";
		 
		mSecondsArgs1 = "S";
		mSecondsArgs2 = "s";
		mMinutesArgs1 = "M";
		mMinutesArgs2 = "m";
		mHoursArgs1 = "H";
		mHoursArgs2 = "h";
		mInvalidArgs1 = "A";
		mInvalidArgs2 = "*";
		
		mMaxSampleInteger = 7877; // Any Number /*new Random().nextInt(1000)*/
//		mMinSampleInteger = new Random().nextInt(10) ;
		mZeroVal = 0;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	

	@Test
	public void test() {
		
		// Random Method Test  Unit Tests
		assertTrue ("Random value should be greater than or equal to 0", Helpers.RandomFloat() > 0.0f);
		assertTrue ("Random value should be less than or equal to 1", Helpers.RandomFloat() <= 1.0f);
//		assertTrue ("output should be greater than 0", Helpers.RandomFloat() > 0.1f);
		
		// parseSimulationCmdArg method 
		assertTrue("Input Command Args should be valid ", testCmdInputArgs(mSecondscmdArg1));
		assertTrue("Input Command Args should be valid ", testCmdInputArgs(mSecondcmdArg2));
		assertTrue("Input Command Args should be valid ", testCmdInputArgs(mMinutescmdArg1));
		assertTrue("Input Command Args should be valid ", testCmdInputArgs(mMinutescmdArg2));
		assertTrue("Input Command Args should be valid ", testCmdInputArgs(mHourscmdArg1));
		assertTrue("Input Command Args should be valid ", testCmdInputArgs(mHourscmdArg2));
		assertFalse("Zero test case ", testCmdInputArgs(mZeroCmdArg));
		assertFalse("Invalid Argument   Test Case ", testCmdInputArgs(mInvalidCmdArg));
		
		//Unit Tests for Helper's applyFormualae method 
		assertTrue("Argument should be valid", testFormulae(mMaxSampleInteger, mSecondsArgs1));
		assertTrue("Argument should be valid", testFormulae(mMaxSampleInteger, mSecondsArgs2));
		assertTrue("Argument should be valid", testFormulae(mMaxSampleInteger, mMinutesArgs1));
		assertTrue("Argument should be valid", testFormulae(mMaxSampleInteger, mMinutesArgs2));
		assertTrue("Argument should be valid", testFormulae(mMaxSampleInteger, mHoursArgs1));
		assertTrue("Argument should be valid", testFormulae(mMaxSampleInteger, mHoursArgs2));
		
		assertFalse("Argument should be valid", testFormulae(mMaxSampleInteger, mInvalidArgs1));
		assertFalse("Argument should be valid", testFormulae(mZeroVal,mInvalidArgs2));
	}
	
	public boolean testCmdInputArgs(String input){
		return Helpers.parseSimulationCmdArg(input)  > 0;
	}
	
	public boolean testFormulae(int input, String intervalType) {
		return Helpers.applyFormualae(input, intervalType) > 0;
	}

}
