package com.jiff.incentives.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.jiff.incentives.pages.BasePage;
import com.jiff.incentives.pages.LoginPage;
import com.jiff.incentives.pages.SignUpPage;

public class BaseTest  {

	protected String user=null;
	protected String password=null;
	protected String token=null;
	protected LoginPage login = new LoginPage();
	protected SignUpPage newUser=new SignUpPage();
	
	public BaseTest(String incentiveUser,String incetivePassword) {
		System.out.println("Base Test Constructor with 2 arguments");
		this.user = incentiveUser;
		this.password = incetivePassword;
		
	}
	
	public BaseTest(String incentiveUser, String incetivePassword, String token)  {
	System.out.println("Base Test Constructor with 3 arguments");
	this.user = incentiveUser;
	this.password = incetivePassword;
	this.token=token;
	
	}

	@BeforeTest
	public void launchIncentivesApplication() {
		
		System.out.println("Launching Incentives Application");
		
		if(token!=null && !token.equals(""))
			newUser.newUserRegistration(user, password,token);
		else
			login.login(user, password);
		
	}
	
	@AfterTest
	public void logout(){
		System.out.println("Logout of incentive app from BaseTest");
		BasePage.logout();
		
	}
	
	@AfterSuite
	public void closeIncentivesApplication(){

		System.out.println("close incentive app");
		BasePage.wait(3000);
		BasePage.quit();
		
	}
}
