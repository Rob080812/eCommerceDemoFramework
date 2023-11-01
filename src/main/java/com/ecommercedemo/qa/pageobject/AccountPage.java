package com.ecommercedemo.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

	WebDriver driver;

	@FindBy(linkText = "Edit your account information")
	private WebElement editYourAccountInformation;

	@FindBy(linkText = "My Account")
	private WebElement verifyMyAccount;

	@FindBy(linkText = "Edit your account information")
	private WebElement editAccountCredentials;

	@FindBy(id = "input-firstname")
	private WebElement editFirstName;

	@FindBy(id = "input-lastname")
	private WebElement editLatName;
	
	@FindBy(id = "input-telephone")
	private WebElement editTelephoneNumber;
	
	@FindBy(xpath = "//input[@value=\"Continue\"]")
	private WebElement clickOnSubmitButton;

	public AccountPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public boolean getDisplayStatusOfEditYourAccountInformationOption() {

		boolean displayStatus = editYourAccountInformation.isDisplayed();
		return displayStatus;

	}

	public boolean retrieveVerifyMyAccountText() {
		boolean productNameText = verifyMyAccount.isDisplayed();
		return productNameText;
	}

	public void clickOnEditYourAccountInfoText() {

		editAccountCredentials.click();
	}

	public void editFirstNameTextFeld() {

		editFirstName.clear();
		editFirstName.sendKeys("Test4545");
	}

	public void editLastNameTextField() {
		editLatName.clear();
		editLatName.sendKeys("Smith");
	}
	
	public void editTelephoneField() {
		editTelephoneNumber.clear();
		editTelephoneNumber.sendKeys("1234567890");
	}
	
	public void submitButton() {

		clickOnSubmitButton.click();
	}
}
