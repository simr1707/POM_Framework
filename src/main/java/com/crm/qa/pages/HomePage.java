package com.crm.qa.pages;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {
	@FindBy(xpath="//td[contains(text(),'User: Simran Singh')] ")
	@CacheLookup  // to save the above element in the cache memory to increase the speed of the framework
	public WebElement userNameLabel;
	
	@FindBy(xpath = "//a[contains(text(),'Contacts')]")
	WebElement contactsLink;

	@FindBy(xpath = "//a[contains(text(),'New Contact')]")
	WebElement newContactLink;
	
	@FindBy(xpath = "//a[contains(text(),'Deals')]")
	WebElement dealsLink;
	
	@FindBy(xpath = "//a[contains(text(),'Tasks')]")
	WebElement tasksLink;
	
	// create constructor of homepage so the element would initialize
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	public boolean verifyCorrectUserName() {
		return userNameLabel.isDisplayed();
	}
	public String verifyHomePageTitle() {
		return driver.getTitle();
	}
	
	public ContactsPage clickOnContactsLink() {
		contactsLink.click();
		//what it will return
		return new ContactsPage();
	}
	
	public DealsPage clickOnDealsPage() {
		dealsLink.click();
		return new DealsPage();
	}
	
	public TasksPage clickOnTaskLink() {
		tasksLink.click();
		return new TasksPage(); 
		
	}
	
	public void clickOnNewContactLink() {
		//Actions builder= new Actions(driver);
		//builder.moveToElement(contactsLink).build().perform();
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",newContactLink );
		
		
	}

}
