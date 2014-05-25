package com.jiff.existingregistereduserwithspouse.payouts.tests;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jiff.incentives.pages.BasePage;
import com.jiff.incentives.pages.HomePage;
import com.jiff.incentives.steps.utility.StepTrackerUtility;
import com.jiff.incentives.tests.BaseTest;

public class BeActiveIncentivePayoutTests extends BaseTest {

	HomePage home = new HomePage();
	
	@Parameters({ "userId", "password" })
	public BeActiveIncentivePayoutTests(String incentiveUser,
			String incetivePassword) {
		super(incentiveUser, incetivePassword);
		System.out.println("Be Active Constructor");
	}
	

	@Test
	public void validateBeActiveIncentivePayouts() {
		System.out.println("Validate BeActive Incentive Payouts");
		//home.goHome();
		Map<String, List<String>> actualData = home.getBeActiveIncentivePayouts();
		Map<String, List<String>> expectedData = StepTrackerUtility.beActiveIncentivePayoutsExpectedData();
		assertEquals(actualData, expectedData);
		BasePage.wait(2000);
	}
	
	
	
	

}
