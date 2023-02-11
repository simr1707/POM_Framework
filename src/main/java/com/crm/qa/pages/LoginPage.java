package com.crm.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{
	//page Factory- or object repository
	
	@FindBy(name="username")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement loginBtn;
	
	@FindBy(xpath = "//img[contains(@class,'img-responsive')]")
	WebElement crmLogo;
	
	//to initialize the WebElements we need to create the constructor 
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions: different features
	
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	public Boolean validateCRMImage() {
		return crmLogo.isDisplayed();
	}
	
	public HomePage login(String un, String pwd) {
		username.sendKeys(un);
		password.sendKeys(pwd);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
	    executor.executeScript("arguments[0].click()", loginBtn);
		// after clicking on login it will go to the homepage
		return new HomePage();
	}
	
	
}
