package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	public LoginPageTest() {
		// to call the base call constructor to get the value of prop or to define the properties of base class
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		log.info("*****************************************Starting LoginPageTest**************************************************");
		initialization();
		//create loginPage class object
		loginPage = new LoginPage();
	}
	
	@Test(priority =1)
	public void loginPageTitleTest() {
		log.info("*****************************************loginPageTitleTest**************************************************");
		String title= loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Free CRM - CRM software for customer relationship management, sales, and support.");
	}
	
	@Test(priority =2)
	public void crmLogoImageTest() {
		log.info("*****************************************crmLogoImageTest**************************************************");
		Boolean flag= loginPage.validateCRMImage();
		Assert.assertTrue(flag);
	}
	
	@Test(priority =3)
	public void loginTest() {
		log.info("*****************************************starting loginTest**************************************************");
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@AfterMethod
	public void tearDown() {
		log.info("*****************************************closing browser **************************************************");
		driver.quit();
	}
	

}
