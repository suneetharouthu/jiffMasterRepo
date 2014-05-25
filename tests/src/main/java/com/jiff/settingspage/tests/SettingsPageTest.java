package com.jiff.settingspage.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import junit.framework.Assert;

import com.jiff.incentives.pages.BasePage;
import com.jiff.incentives.pages.HomePage;
import com.jiff.incentives.pages.LoginPage;
import com.jiff.incentives.pages.SettingsPage;
import com.jiff.incentives.tests.BaseTest;

public class SettingsPageTest extends BaseTest {
	
	private HomePage home=new HomePage();
	private LoginPage login=new LoginPage();
	private SettingsPage settings=new SettingsPage();
	

	@Parameters({ "userId", "password" })
	public SettingsPageTest(String user, String password){
		super(user,password);
		
	}
	/*
	 * this method will update first name and last name in settings page
	 * and verifies updates are successful or not
	 */
	@Test
	public void updateNameTest( ){
		String orignalFirstName = settings.getFirstName();
		String originalLastName = settings.getLastName();
		
		String firstName="Michael";
		String lastName="Jackson";
		
		home.getSettingsPage();
		String email=settings.getCurrentEmail();
		Assert.assertEquals(email.trim(),user);
		
		settings.updateName(firstName,lastName);
		
		BasePage.logout();
		login.login(user, password);
		home.getSettingsPage();
		
			
		String actualFirstName= settings.getFirstName();
		Assert.assertEquals(actualFirstName,firstName);
		
		String actualLastName=settings.getLastName();
		Assert.assertEquals(actualLastName,lastName);
		
		//Reset user
		settings.updateName(orignalFirstName,orignalFirstName);
	}
	
	/*
	 * this method will update password and verifies update successful or not
	 */
	@Test
	public void updatePasswordTest( ){
	
		String newPassword="password2";
		home.getSettingsPage();
		settings.updatePassword(password, newPassword);
		BasePage.logout();
		login.login(user, newPassword);
		Assert.assertTrue(home.isHomeLinkPresent());
		
		//Reset Password
		home.getSettingsPage();
		settings.updatePassword(newPassword, password);
		BasePage.logout();
		login.login(user, password);
		Assert.assertTrue(home.isHomeLinkPresent());
		
	}
	
	

}

