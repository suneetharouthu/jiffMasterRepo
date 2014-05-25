package com.jiff.existingregistereduserwithspouse.dashboard.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jiff.incentives.calendar.utility.CalendarUtility;
import com.jiff.incentives.pages.HomePage;
import com.jiff.incentives.tests.BaseTest;

public class DashboardStepTests extends BaseTest {
	
	
	HomePage home = new HomePage();
	
	@Parameters({ "userId", "password" })
	public DashboardStepTests(String incentiveUser,
			String incetivePassword) {
		super(incentiveUser, incetivePassword);
		System.out.println("Dash Board Constructor");

	}
	
	@Test
	public void validateDaysLeft(){
		String expectedDaysLeft = CalendarUtility.expectedDaysLeft();
		String actualDaysLeft = home.actualDaysLeft();
		
		System.out.println("Checking if expectedDays  :" +expectedDaysLeft+" is same as actualDaysLeft left "+actualDaysLeft);
		assertEquals(expectedDaysLeft,actualDaysLeft);
		
	}
	
/*	@Test
	public void validateEarningsActivities(){
		
		
	}*/
	
	@Test
	public void validateStepsTaken(){
		
		home.syncSteps();
		
		String expectedStepsLeft = home.expectedStepsLeft();
		String actualStepsLeft = home.actualStepsLeftToGoal();
		
		System.out.println("Checking if expected steps left : "+ expectedStepsLeft + " is same as actual steps left :"+actualStepsLeft);
		assertEquals(actualStepsLeft,expectedStepsLeft);
	}
	
	

}
