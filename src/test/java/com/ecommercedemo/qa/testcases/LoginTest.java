package com.ecommercedemo.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ecommercedemo.qa.base.Base;
import com.ecommercedemo.qa.pageobject.AccountPage;
import com.ecommercedemo.qa.pageobject.HomePage;
import com.ecommercedemo.qa.pageobject.LoginPage;
import com.tutorialninja.qa.utils.Utilities;

public class LoginTest extends Base {
	
	LoginPage loginPage;

	public LoginTest() {
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

	@Test(priority = 1, dataProvider = "validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) throws InterruptedException {

		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		AccountPage accountPage = loginPage.clickonLoginButton();

		Thread.sleep(9000);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),
				"Edit your account information is not displayed");

	}

	@DataProvider(name = "validCredentialsSupplier")
	public Object[][] supplyTestData() {

		Object[][] data = Utilities.getTestDataFromExcel("Login");

		return data;

	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() throws InterruptedException {

		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickonLoginButton();

		Thread.sleep(9000);
		String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("warningMessage");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected Warning Message is not displayed");

	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword() throws InterruptedException {

		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickonLoginButton();

		Thread.sleep(9000);
		String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("warningMessage");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected Warning Message is not displayed");

	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidPassword() throws InterruptedException {

		loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickonLoginButton();

		Thread.sleep(9000);
		String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("warningMessage");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected Warning Message is not displayed");

	}

	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials() throws InterruptedException {

		Thread.sleep(6000);
		loginPage.clickonLoginButton();
		Thread.sleep(9000);
		String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("warningMessage");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected Warning Message is not displayed");

	}

}
