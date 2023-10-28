package com.ecommercedemo.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	WebDriver driver;

	@FindBy(linkText = "HP LP3065")
	WebElement verifyProductName;

	@FindBy(xpath = "//div[@id='content']/h2/following-sibling::p")
	WebElement noProductWarningMessage;

	public SearchPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean retrieveVerifyProductNameText() {
		boolean productNameText = verifyProductName.isDisplayed();
		return productNameText;
	}

	public String retrieveNoProductWarningMessage() {
		String noProductWarningMessageText = noProductWarningMessage.getText();
		return noProductWarningMessageText;
	}

}
