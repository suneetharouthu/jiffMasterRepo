package com.jiff.newuserwithspouse.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jiff.incentives.food.utility.FoodTrackerUtility;
import com.jiff.incentives.has.utility.HsaUtility;
import com.jiff.incentives.pages.HomePage;
import com.jiff.incentives.pages.NewUserHomePage;
import com.jiff.incentives.pages.WelcomePage;
import com.jiff.incentives.steps.utility.StepTrackerUtility;
import com.jiff.incentives.tests.BaseTest;

public class NewUserExperienceTest extends BaseTest {

	private String seedAmount = null;
	private String limit = null;
	private String signUpAmount = null;
	private String activationAmount = null;
	private String spouseEmail=null;

	private WelcomePage welcomePage = new WelcomePage();
	private NewUserHomePage newHomePage = new NewUserHomePage();
	private HomePage homePage = new HomePage();

	@Parameters({ "userId", "password", "token","spouseEmail", "seed", "limit",
			"signupAmount", "activationAmount" })
	public NewUserExperienceTest(String incentiveUser, String incetivePassword,
			String token, String spouseEmail,String seed, String limit, String signup,
			String activation) {
		super(incentiveUser, incetivePassword, token);
		this.spouseEmail=spouseEmail;
		this.seedAmount = seed;
		this.limit = limit;
		this.signUpAmount = signup;
		this.activationAmount = activation;
	}

	//@Test
	public void validateNewUserWelcomePageTest() {

		assertTrue(welcomePage.areKeyWelcomePageElementsPresent());

		String hsaEarning = HsaUtility.getExpectedEarningsFromHsa(seedAmount);
		String incetivesLeft = HsaUtility.getExpectedRemainingIncentives(limit,
				activationAmount);

		String exptectedGetPaidMessage = "You scored $" + hsaEarning
				+ " from ABLife towards your HSA. Earn an additional $"
				+ incetivesLeft+".";
		String actualGetPaidMessage = welcomePage.getActualPaidMessage();

		assertEquals(actualGetPaidMessage.trim(),exptectedGetPaidMessage.trim());

		welcomePage.gotoNewUserHomePage();

	}

	@Test
	public void validateNewUserHomePageBeActiveTest() {

		assertTrue(newHomePage.areKeyNewUserHomePageElementsPresent());

		Map<String, List<String>> actualincentivePayoutList = homePage.getBeActiveIncentivePayouts();
		Map<String, List<String>> expectedIncentivePayoutList = StepTrackerUtility
				.beActiveIncentivePayoutsExpectedData();
		assertEquals(actualincentivePayoutList, expectedIncentivePayoutList);

		String[] actualChooseOnemenuList = newHomePage.getActualActiveScreenChooseOneList();
		String[] expectedChooseOnemenuList = { "Pick up your Fitbit","Get an activity tracker", "Use my own activity tracker","Skip for now" };
		assertEquals(actualChooseOnemenuList, expectedChooseOnemenuList);

		newHomePage.skipNow();
		String actualText = newHomePage.getTextAfterSkip();
		String expectedText = "I'm skipping activity tracking";
		assertEquals(actualText, expectedText);
		assertTrue(newHomePage.isChangeItButtonPresent());
		newHomePage.changeIt();
		assertTrue(newHomePage.isChooseOneButtonPresent());
		
		//Link Account with Nike Fuel Band Device
		newHomePage.useMyOwnActivityTracker();
		assertTrue(newHomePage.isMTDAvgisPresent());
	}

	//@Test
	public void inviteSpouseByEmailTest() {
		newHomePage.inviteSpouse(spouseEmail);
	}


	@Test
	public void validateNewUserHomePageFoodTrackTest() {
		newHomePage.trackFoodSkipForNow();

		assertTrue(newHomePage.isTrackFoodChangeItButtonPresent());

		newHomePage.clickFoodTrackerChangeItButton();

		assertTrue(newHomePage.isTrackFoodChooseOneButtonPresent());

		//Linking Account with FatSecret
		newHomePage.linkAccountWithFatSecret();
		
		assertTrue(newHomePage.isTrackFoodWeeklyGoalPresent());
		
		Map<String, List<String>> actualData = homePage.getFoodTrackerIncentivePayouts();
		Map<String, List<String>> expectedData = FoodTrackerUtility.foodTrackerIncentivePayoutsExpectedData();
		assertEquals(actualData, expectedData);
		
		String[] actualTrackFoodChooseOnemenuList=newHomePage.getActualTrackFoodScreenChooseOneList();
		String[] expectedTrackFoodChooseOnemenuList={"set up food tracker","skip for now"};
		assertEquals(actualTrackFoodChooseOnemenuList,expectedTrackFoodChooseOnemenuList);
	

	}

}
