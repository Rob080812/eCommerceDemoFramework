package com.ecommercedemo.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	//Objects
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText="Login")
	private WebElement loginOption;
	
	@FindBy(linkText="Register")
	private WebElement registerOption;
	
	@FindBy(name="search")
	WebElement validProductName;
	
	@FindBy(xpath="//div[@id='search']/descendant::button")
	WebElement searchbutton;
	
	@FindBy(name="search")
	WebElement inValidProductName;
	
	public HomePage (WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	public void clickOnMyAccount() {
		
		myAccountDropMenu.click();
		
	}
	
	public LoginPage selectLoginOption() {
		
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage selectRegisterOption() {
		
		registerOption.click();
		return new RegisterPage(driver);
	}
	
	public void enterValidProductName (String productName) {
		validProductName.sendKeys(productName);
	}
	
	public SearchPage clickOnSearchButton() {
		searchbutton.click();
		return new SearchPage(driver);
	}
	
	public void enterInValidProductName (String productName) {
		inValidProductName.sendKeys(productName);
	}

}
