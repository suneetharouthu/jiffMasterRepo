package com.jiff.existingregistereduserwithspouse.payouts.tests;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jiff.incentives.food.utility.FoodTrackerUtility;
import com.jiff.incentives.pages.BasePage;
import com.jiff.incentives.pages.HomePage;
import com.jiff.incentives.tests.BaseTest;

import static org.testng.Assert.assertEquals;

public class FoodTrackingIncentivePayoutTests extends BaseTest{

	HomePage home = new HomePage();
	
	@Parameters({ "userId", "password" })
	public FoodTrackingIncentivePayoutTests(String incentiveUser,
			String incetivePassword) {
	
		super(incentiveUser, incetivePassword);
		System.out.println("Food tracking Constructor");
	}
	

	@Test
	public void validateFoodTrackingIncentivePayouts() {
		System.out.println("Validate Food Tracking Incentive Payouts");
		//home.goHome();
		Map<String, List<String>> actualData = home.getFoodTrackerIncentivePayouts();
		Map<String, List<String>> expectedData = FoodTrackerUtility.foodTrackerIncentivePayoutsExpectedData();
		assertEquals(actualData, expectedData);
		BasePage.wait(2000);

	}
	
}
