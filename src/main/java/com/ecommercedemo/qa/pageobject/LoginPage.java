package com.ecommercedemo.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	// Objects
	@FindBy(id = "input-email")
	private WebElement emailAddressField;

	@FindBy(id = "input-password")
	private WebElement passwordField;
	
	@FindBy(xpath="//*[@id=\"content\"]/div/div[2]/div/form/input")
	private WebElement loginbutton;
	
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNotMatching;

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//Actions

	public void enterEmailAddress(String emailText) {

		emailAddressField.sendKeys(emailText);

	}

	public void enterPassword(String passwordText) {

		passwordField.sendKeys(passwordText);
		
		
	}
	
	public AccountPage clickonLoginButton() {
		
		loginbutton.click();
		return new AccountPage(driver);
		
	}
	
	public String retrieveEmailPasswordNotMatchingWarningMessageText() {
		
		String warningText = emailPasswordNotMatching.getText();
		return warningText;
		
	}
}
