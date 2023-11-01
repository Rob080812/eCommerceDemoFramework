package com.ecommercedemo.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ecommercedemo.qa.base.Base;
import com.ecommercedemo.qa.pageobject.AccountPage;
import com.ecommercedemo.qa.pageobject.HomePage;
import com.ecommercedemo.qa.pageobject.LoginPage;

public class MyAccountTest {
	
	public class MyAccount extends Base {
		
		LoginPage loginPage;
		AccountPage accountPage;

		public MyAccount() {
			super();
		}

		public WebDriver driver;
		
		@BeforeMethod
		public void setUp() throws InterruptedException {

			driver = initializeBrowserAndOpenApplictaionURL(prop.getProperty("browserName"));
			HomePage homePage = new HomePage(driver);
			Thread.sleep(9000);
			homePage.clickOnMyAccount();
			loginPage = homePage.selectLoginOption();
			

		}

		@AfterMethod
		public void tearDown() {

			driver.quit();
			System.out.println("Teardown Successful");
		}

		@Test(priority = 1)
		public void verifyAccountWithValidLogin() throws InterruptedException {

			loginPage.enterEmailAddress(prop.getProperty("validEmail"));;
			loginPage.enterPassword(prop.getProperty("validPassword"));
			accountPage = loginPage.clickonLoginButton();
			Thread.sleep(9000);
			Assert.assertTrue(accountPage.retrieveVerifyMyAccountText(),
					"My Account Text is not displayed");

		}
		
		@Test(priority = 2)
		public void editFirstNameTextField() throws InterruptedException {
			
			loginPage.enterEmailAddress(prop.getProperty("validEmail"));;
			loginPage.enterPassword(prop.getProperty("validPassword"));
			accountPage = loginPage.clickonLoginButton();
			Thread.sleep(9000);
			accountPage.clickOnEditYourAccountInfoText();
			Thread.sleep(4000);
			accountPage.editFirstNameTextFeld();
			
			accountPage.editFirstNameTextFeld();
			accountPage.editLastNameTextField();
			accountPage.editTelephoneField();
			accountPage.submitButton();
			
			
		}
		
		
	}
}
