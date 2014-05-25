package com.jiff.existingregistereduserwithspouse.dashboard.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jiff.incentives.pages.BasePage;
import com.jiff.incentives.pages.HomePage;
import com.jiff.incentives.tests.BaseTest;

public class SpouseDashboardStepTests extends BaseTest {
	
	private String spouseId;
	private String spousePassword;
	private static String employeeActualDaysLeft;
	private static String spouseActualDaysLeft; 
	private static String employeeStepInfo ;
	private static String spouseStepInfo ;
	private static String employeeStepInfoWithSpouse;
	private static String spouseStepInfoWithEmployee;
	
	HomePage home = new HomePage();
	
	@Parameters({ "userId", "password","spouseId","spousePassword" })
	public SpouseDashboardStepTests(String incentiveUser,
			String incetivePassword,String spouse,String spousePassword) {
		super(incentiveUser, incetivePassword);
		this.spouseId=spouse;
		this.spousePassword=spousePassword;
		System.out.println("Dash Board Constructor");

	}
	

	@Test
	public void getStepsForRegisteredUserAndSpouse(){
		System.out.println("@Test getStepsForRegisteredUserAndSpouse");
		
		System.out.println("@Before Class syncRegisteredUserandSpouse");
		employeeActualDaysLeft = home.actualDaysLeft();
		System.out.println("Employee Actual Days Left "+employeeActualDaysLeft );
		home.syncSteps();
		employeeStepInfo = home.getStepInfo();
		
		String[] employeeStepInfoArray = employeeStepInfo.split("GOAL");
		String employeeSteps = employeeStepInfoArray[0].trim();
		BasePage.logout();
		BasePage.wait(2000);
		login.login(spouseId, spousePassword);
		spouseActualDaysLeft = home.actualDaysLeft();
		System.out.println("Spouse Actual Days Left "+spouseActualDaysLeft );
		assertEquals(employeeActualDaysLeft,spouseActualDaysLeft);
		home.syncSteps();
		spouseStepInfo = home.getStepInfo();
		String[] spouseStepsArray = spouseStepInfo.split("GOAL");
		String spouseSteps = spouseStepsArray[0].trim();
		employeeStepInfoWithSpouse = home.getSpouseStepInfo().trim();
		
		BasePage.logout();
		BasePage.wait(2000);
		login.login(user, password);
		BasePage.wait(2000);
		spouseStepInfoWithEmployee=home.getSpouseStepInfo().trim();
	
		assertEquals(employeeSteps,employeeStepInfoWithSpouse);
		assertEquals(spouseSteps,spouseStepInfoWithEmployee);
	}

}
