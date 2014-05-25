package com.jiff.homepage.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jiff.incentives.pages.HomePage;
import com.jiff.incentives.pages.LoginPage;
import com.jiff.incentives.tests.BaseTest;

public class FoodTrackTest extends BaseTest {
	public LoginPage login=new LoginPage();
	public HomePage home=new HomePage();
	By foodTrackGraph=By.xpath("//*[@id='js-food-summaries-modal']/div");
	
	@Parameters({ "userId", "password" })
	public FoodTrackTest(String user,String password){
		super(user,password);
	}


	@Test
	public void trackFoodSyncTest(){		
		String successMessage=home.syncFoodTrack();
		Assert.assertEquals(successMessage, "Food tracking data sync successful");	

	}
	@Test
	public void trackFoodViewGraphTest(){
	
		home.getTrackFoodViewGraph();
		Assert.assertTrue(home.isFoodTrackViewGraphDisplayed());
		home.closeFoodTrackGraph();
		
	}
}
