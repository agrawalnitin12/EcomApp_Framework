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

	LoginPage page;
	ContactsPage contactsPage;
	HomePage hPage;
	TestUtil testUtil;
	String sheetName = "Contacts";

	public ContactsPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		page = new LoginPage();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		hPage = page.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		contactsPage = hPage.clickOnContacts();
	}

	@Test(priority = 1)
	public void verifyContactsLabel() {
		Assert.assertTrue(contactsPage.verifyContactsLabel());
	}

	@DataProvider
	public Object[][] getCRMTestData() {
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}

	/**
	 * @param title
	 * @param fName
	 * @param lName - press shift+alt+j to get this comment line
	 * @param Comp
	 */
	@Test(priority = 2, dataProvider = "getCRMTestData")
	public void validateCreateNewContact(String title, String fName, String lName, String Comp) {
		hPage.clickOnNewContactLink();
		contactsPage.createNewContact(title, fName, lName, Comp);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
