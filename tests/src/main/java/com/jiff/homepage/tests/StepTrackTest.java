package com.jiff.homepage.tests;

import java.util.Map;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jiff.incentives.pages.BasePage;
import com.jiff.incentives.pages.HomePage;
import com.jiff.incentives.pages.LoginPage;
import com.jiff.incentives.steps.utility.StepTrackerUtility;
import com.jiff.incentives.tests.BaseTest;

public class StepTrackTest extends BaseTest {
	public BasePage base = new BasePage();
	public LoginPage login = new LoginPage();
	public HomePage home = new HomePage();
	By beActiveViewGraph=By.xpath("//*[@id='js-activity-summaries-modal']/div");

	
	@Parameters({ "userId", "password" })
	public StepTrackTest(String user,String password){
		super(user,password);
	}


	// tests Active incentive payouts is as expected
	@Test
	public void activeIncentivePayoutTest() {
		BasePage.wait(3000);
		Map actualData = home.activeIncentivePayouts();
		Map expectedData = StepTrackerUtility.beActiveIncentivePayoutsExpectedData();
		System.out.println(expectedData);
		Assert.assertEquals(actualData, expectedData);
		BasePage.wait(3000);
	}

	// tests reset goal option in Be Active
/*	@Test()
	public void resetGoalsTest() {
		BasePage.wait(3000);
		String EXPECTED = null;
		String SETGOAL = BasePage.CONFIG.getProperty("SETGOAL");
		String Goal = home.beActiveResetGoal(SETGOAL);

		if (SETGOAL.equals("1")) {
			EXPECTED = BasePage.CONFIG.getProperty("goal1");
		} else if (SETGOAL.equals("2")) {
			EXPECTED = BasePage.CONFIG.getProperty("goal2");
		} else if (SETGOAL.equals("3")) {
			EXPECTED = BasePage.CONFIG.getProperty("goal3");

		}

		Assert.assertEquals(Goal, EXPECTED);
		BasePage.wait(3000);
	}*/

	// tests sync option in Be Active
	@Test
	public void beActiveSyncTest() {
	
		String successMessage = 	home.syncSteps();
		Assert.assertEquals(successMessage, "Device data sync successful");
		BasePage.wait(3000);
	}

/*	@Test
	public void beActiveViewGraphTest() {
		home.beActiveViewGraph();
		BasePage.wait(4000);
		boolean result = BasePage.isElementPresent(beActiveViewGraph);
		Assert.assertEquals(result, true);
		BasePage.driver.findElement(By.xpath("//*[@id='js-activity-summaries-modal']/div/div/div[3]/button")).click();
		BasePage.wait(3000);

	}*/

}

