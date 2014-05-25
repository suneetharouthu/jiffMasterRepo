package com.jiff.homepage.tests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jiff.incentives.pages.HomePage;
import com.jiff.incentives.pages.LoginPage;
import com.jiff.incentives.tests.BaseTest;

public class EmployeeAndSpouseInfoComparisionTest extends BaseTest {

	HomePage home = new HomePage();
	LoginPage login = new LoginPage();

	// Opens browser and login

	@Parameters({ "userId", "password" })
	public EmployeeAndSpouseInfoComparisionTest(String user, String password) {
		super(user, password);

	}

	@Test
	public void employeeAndSpouswInfoComparisionTest() {
		employeeAndSpouseInfoComparision();
	}

	public void employeeAndSpouseInfoComparision() {

		home.getEmployeeInfo();
		home.logout();
		login.login(user, password);
		home.getSpouseInfo();
		employeeAndSpouseInfoValidations();

	}

	public void employeeAndSpouseInfoValidations() {
		System.out.println("This is Employee And Spouse Info Validations");
		try {
			Assert.assertEquals(home.employee_totalEarnings,
					home.spouse_totalEarnings);
			System.out.println("Total Eranings are same");

		} catch (Exception e) {
			System.err.println("Total Eranings are not same");
		}

		try {
			Assert.assertEquals(home.employee_currentMonth,
					home.spouse_currentMonth);
			System.out.println("currentMonth is  same");

		} catch (Exception e) {
			System.err.println("currentMonth is not same");
		}
		try {
			Assert.assertEquals(home.employee_currentMonthYear,
					home.spouse_currentMonthYear);
			System.out.println("currentMonthYear is  same");

		} catch (Exception e) {
			System.err.println("currentMonthYear is not same");
		}
		try {
			Assert.assertEquals(home.employee_daysLeft, home.spouse_daysLeft);
			System.out.println("daysLeft is  same");

		} catch (Exception e) {
			System.err.println("daysLeft is not same");
		}

		try {
			Assert.assertEquals(home.employee_empAVGsteps,
					home.spouse_empAVGsteps);
			System.out.println("employee AVG steps  is  same");

		} catch (Exception e) {
			System.err.println("employee AVG steps is not same");
		}
		try {
			Assert.assertEquals(home.employee_empAVGtf, home.spouse_empAVGtf);
			System.out.println("employee AVG track food  is  same");

		} catch (Exception e) {
			System.err.println("employee AVG track food is not same");
		}
		try {
			Assert.assertEquals(home.employee_spouseAVGsteps,
					home.spouse_spouseAVGsteps);
			System.out.println("Spouse AVG steps  is  same");

		} catch (Exception e) {
			System.err.println("Spouse AVG steps is not same");
		}
		try {
			Assert.assertEquals(home.employee_spouseAvgtf,
					home.spouse_spouseAvgtf);
			System.out.println("Spouse AVG track food  is  same");

		} catch (Exception e) {
			System.err.println("Spouse AVG track food is not same");
		}
		try {
			Assert.assertEquals(home.employee_myEarningsText,
					home.spouse_myEarningsText);
			System.out.println("myEarningsText  is  same");

		} catch (Exception e) {
			System.err.println("myEarningsText is not same");
		}
		try {
			Assert.assertEquals(home.employee_EarningsPageElements,
					home.spouse_EarningsPageElements);
			System.out.println("Earning page elements are same");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
