package com.ecommercedemo.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	@FindBy(id = "input-email")
	private WebElement emailAddressField;

	@FindBy(id = "input-telephone")
	private WebElement telephoneField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordField;

	@FindBy(name="agree")
	private WebElement privacyPolicyField;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='0']")
	private WebElement NewsletterOptionYes;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameValidation;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameValidation;
	
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement invalidEmailAddress;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement invalidTelephoneNumber;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement invalidPassword;

	public RegisterPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
		
	}
	
	public void enterLastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
		
	}
	
	public void enterEmailAddress(String emailText) {
		emailAddressField.sendKeys(emailText);
		
	}
	public void enterTelephoneNumber(String telephoneText) {
		telephoneField.sendKeys(telephoneText);
		
	}
	
	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
	
	public void enterConfirmPassword(String passwordText) {
		confirmPasswordField.sendKeys(passwordText);
	}
	
	public void selectPrivacyOption() {
		privacyPolicyField.click();
	}
	
	public AccountSuccessPage selectContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public void selectNewsletterOptionYes() {
		NewsletterOptionYes.click();
	}
	
	public String retrieveDuplicateEmailAddressWarning() {
		String duplicateEmailWarningText = duplicateEmailAddressWarning.getText();
		return duplicateEmailWarningText;
	}
	
	public String retrievePrivacyPolicyWarning() {
		String privacyPolicyWarningText = privacyPolicyWarning.getText();
		return privacyPolicyWarningText; 
	}
	
	public String retrievFirstNameValidationMessage() {
		String firstNameValidationText = firstNameValidation.getText();
		return firstNameValidationText;
	}
	
	public String retrievelastNameValidationMessage() {
		String lastNameValidationText = lastNameValidation.getText();
		return lastNameValidationText;
	}
	
	public String retrieveinvalidEmailAddressMessage() {
		String invalidEmailAddressText = invalidEmailAddress.getText();
		return invalidEmailAddressText;
	}
	
	public String retrieveinvalidTelephoneNumberMessage() {
		String invalidTelephoneNumberText = invalidTelephoneNumber.getText();
		return invalidTelephoneNumberText;
	}
	
	public String retrieveinvalidPasswordMessage() {
		String invalidPasswordText = invalidPassword.getText();
		return invalidPasswordText;
		
	}
}
