package com.ecommercedemo.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ecommercedemo.qa.base.Base;
import com.ecommercedemo.qa.pageobject.AccountSuccessPage;
import com.ecommercedemo.qa.pageobject.HomePage;
import com.ecommercedemo.qa.pageobject.RegisterPage;
import com.tutorialninja.qa.utils.Utilities;

public class RegistrationTest extends Base {
	
	AccountSuccessPage accountSuccessPage;
	
	RegisterPage registerPage;
	
	public RegistrationTest() {
		super();
	}
	

	public WebDriver driver;

	@BeforeMethod
	public void setUp() throws InterruptedException {

		driver = initializeBrowserAndOpenApplictaionURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		registerPage = homePage.selectRegisterOption();
		
		Thread.sleep(4000);

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
		System.out.println("Teardown Successful");
	}

	@Test(priority = 1)
	public void verifyRegistrationWithMandatoryFields() throws InterruptedException {
		
		//below scripts can be reduced by creating a separate method in RegisterPage
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivacyOption();
		accountSuccessPage = registerPage.selectContinueButton();
		Thread.sleep(8000);
		
		String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountCreatedMessage"),
				"Account heading page is not displayed");
		System.out.println(actualSuccessHeading);

	}

	@Test(priority = 2)
	public void verifyRegistrationWithAllFields() throws InterruptedException {
		
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectNewsletterOptionYes();
		registerPage.selectPrivacyOption();
		accountSuccessPage = registerPage.selectContinueButton();
		Thread.sleep(8000);
		
		String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountCreatedMessage"),
				"Account heading page is not displayed");
		System.out.println(actualSuccessHeading);
	}

	@Test(priority = 3)
	public void verifyRegistrationWithExistingAccount() throws InterruptedException {
		
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(prop.getProperty("validEmail"));
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectNewsletterOptionYes();
		registerPage.selectPrivacyOption();
		registerPage.selectContinueButton();
		Thread.sleep(8000);
		String actualWarningMessage = registerPage.retrieveDuplicateEmailAddressWarning();
		Assert.assertEquals(actualWarningMessage, dataProp.getProperty("emailAlreadyRegisteredMessage"),
				"Expected Warning Message is not displayed");
		System.out.println(actualWarningMessage);

	}

	@Test(priority = 4)
	public void verifyRegistrationWithoutEnteringAnyFields() throws InterruptedException {
		
		registerPage.selectContinueButton();
		
		Thread.sleep(8000);

		String actualWarningMessage = registerPage.retrievePrivacyPolicyWarning();
		Assert.assertEquals(actualWarningMessage, dataProp.getProperty("privacyPolicyWarning"),
				"Expected Warning Message is not displayed");
		System.out.println(actualWarningMessage);

		String actualFirstName = registerPage.retrievFirstNameValidationMessage();
		Assert.assertEquals(actualFirstName, dataProp.getProperty("firstNameValidation"),
				"First name warning message is not displayed");
		System.out.println(actualFirstName);

		String actualLastName = registerPage.retrievelastNameValidationMessage();
		Assert.assertEquals(actualLastName, dataProp.getProperty("lastNameValidation"),
				"Last Name warning message is not displayed");
		System.out.println(actualLastName);

		String actualEmail = registerPage.retrieveinvalidEmailAddressMessage();
		Assert.assertEquals(actualEmail, dataProp.getProperty("invalidEmailAddress"),
				"Email warning message is not displayed");
		System.out.println(actualEmail);

		String actualTelephone = registerPage.retrieveinvalidTelephoneNumberMessage();
		Assert.assertEquals(actualTelephone, dataProp.getProperty("invalidTelephoneNumber"),
				"Telephone warning message is not displayed");
		System.out.println(actualTelephone);

		String actualPassword = registerPage.retrieveinvalidPasswordMessage();
		Assert.assertEquals(actualPassword, dataProp.getProperty("invalidPassword"),
				"Password warning message is not displayed");
		System.out.println(actualPassword);
	}

}
