package com.crm.qa.testcases;    
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	// create constructor of HomePageTest
	public HomePageTest() {
		super(); // to call the base class constructor to initialize the properties
	}
	
	@BeforeMethod
	public void setUp() {
		log.info("*****************************************Starting HomePageTest TestCases **************************************************");
		initialization();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Test(priority=1)
	public void verifyHomePageTitleTest(){
		log.info("*****************************************Starting verifyHomePageTitleTest **************************************************");
		//wait.until(ExpectedConditions.visibilityOf(homePage.userNameLabel));
		String homePageTitle =homePage.verifyHomePageTitle();  	
		Assert.assertEquals(homePageTitle, "CRMPRO","Home page title not matched");
	}
	
	@Test(priority=2)
	public void verifyUserNameTest(){
		log.info("*****************************************verifyUserNameTest **************************************************");
		testUtil.switchToFrame();
		Assert.assertTrue(homePage.verifyCorrectUserName());
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest() {
		log.info("*****************************************verifyContactsLinkTest **************************************************");
		testUtil.switchToFrame();
		contactsPage=homePage.clickOnContactsLink();
	}
	
	@AfterMethod
	public void tearDown() {
		log.info("*****************************************closing browser **************************************************");
		driver.quit();
	}
	

}
