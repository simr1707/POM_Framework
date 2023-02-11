package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	String sheetName= "contacts";
	
	public ContactsPageTest() {
		// to call the base call constructor to get the value of prop or to define the properties of base class
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		contactsPage=homePage.clickOnContactsLink();		
	}
	
	@Test(priority=1)
	public void verifyContactsPageLabel() {
		Assert.assertTrue(contactsPage.verifyContactsLabel(),"contacts label is not present");
	}
	
	@Test(priority=2)
	public void selectSingleContactsTest() {
		contactsPage.selectContactsByName("Amar Singla");
	}
	
	@Test(priority=3)
	public void selectMultipleContactsTest() {
		contactsPage.selectContactsByName("Amar Singla");
		contactsPage.selectContactsByName("Gur Sidhu");
	}
	
	@DataProvider
	public Object[][] getCRMTestData(){
		Object data[][] =TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=4, dataProvider="getCRMTestData" )
	public void validateCreateNewContact(String title, String firstname, String lastname, String company){
		homePage.clickOnNewContactLink();
		//contactsPage.createNewContact("Mr.", "pardeep", "singh", "google");
		contactsPage.createNewContact(title, firstname, lastname, company);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	}
