package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.TasksPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase {
	
	LoginPage page;
	HomePage hPage;
	TestUtil testUtil;
	ContactsPage contactPage;
	DealsPage dealsPage;
	TasksPage tasksPage;

	public HomePageTest() {
		super();
	}
	//Test cases should be separated - independent with each other
	//before each test case launch browser & login 
	//@Test -- execute test case 
	//after each test case close the browser
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		page = new LoginPage();
		testUtil = new TestUtil();
		contactPage = new ContactsPage();
		dealsPage = new DealsPage();
		tasksPage = new TasksPage();
		hPage = page.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test (priority = 1)
	public void verifyTitle() {
	String title = hPage.validateTitle();
	Assert.assertEquals(title, "CRMPRO", "HomePage title didnot match");
	}
	
	@Test (priority = 2)
	public void verifyUser() {
		testUtil.switchToFrame();
		Assert.assertTrue(hPage.verifyUserName());
	}
	
	@Test (priority = 3)
	public void verifyContactLinkTest() {
		testUtil.switchToFrame();
		contactPage = hPage.clickOnContacts();
	}
	
	@Test (priority = 4)
	public void verifyDealLinkTest() {
		testUtil.switchToFrame();
		dealsPage = hPage.clickOnDeals();
	}
	
	@Test (priority = 5)
	public void verifyTaskLinkTest() {
		testUtil.switchToFrame();
		tasksPage = hPage.clickOnTasks();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	

}
