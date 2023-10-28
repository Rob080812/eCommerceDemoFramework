package com.ecommercedemo.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ecommercedemo.qa.base.Base;
import com.ecommercedemo.qa.pageobject.HomePage;
import com.ecommercedemo.qa.pageobject.SearchPage;

public class SearchTest extends Base {
	
	SearchPage searchPage;
	HomePage homePage;
	
	public SearchTest() {
		super();
	}
	
	public WebDriver driver;

	@BeforeMethod
	public void setUp() throws InterruptedException {

		driver = initializeBrowserAndOpenApplictaionURL(prop.getProperty("browserName"));
		homePage = new HomePage(driver);
		Thread.sleep(6000);

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
		System.out.println("Teardown Successful");
	}
	
	@Test (priority=1)
	public void verfiySearchWithValidProduct () throws InterruptedException {
		
		homePage.enterValidProductName(dataProp.getProperty("validProductName"));
		searchPage = homePage.clickOnSearchButton();
		Thread.sleep(8000);
		Assert.assertTrue(searchPage.retrieveVerifyProductNameText());
	}
	
	@Test (priority=2)
	public void verifySearchWithInvalidProduct () throws InterruptedException {
		
		homePage.enterInValidProductName(dataProp.getProperty("invalidproductName"));
		searchPage = homePage.clickOnSearchButton();
		
		Thread.sleep(8000);
		String actualSearchErrorMessage = searchPage.retrieveNoProductWarningMessage();
		Assert.assertEquals(actualSearchErrorMessage, dataProp.getProperty("searchWarningMessage"),
				"Product matches error page is not displayed");
		System.out.println(actualSearchErrorMessage);
		
	}
	
	@Test (priority=3, dependsOnMethods={"verifySearchWithInvalidProduct"} )
	public void verifySearchWithNoProduct () throws InterruptedException {
		
		searchPage = homePage.clickOnSearchButton();
		Thread.sleep(8000);
		String actualSearchErrorMessage = searchPage.retrieveNoProductWarningMessage();
		Assert.assertEquals(actualSearchErrorMessage, dataProp.getProperty("searchWarningMessage"),
				"Product matches error page is not displayed");
		System.out.println(actualSearchErrorMessage);
		
	}

}
