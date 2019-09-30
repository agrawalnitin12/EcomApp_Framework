package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {

	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//td[contains(text(),'Contacts')]")
	WebElement contactsLabel;

	@FindBy(name = "title")
	WebElement ntitle;

	@FindBy(name = "first_name")
	WebElement fName;

	@FindBy(name = "surname")
	WebElement lName;

	@FindBy(name = "client_lookup")
	WebElement comp;

	@FindBy(xpath = "//td[@colspan='2']/input[@type='submit' and @value='Save']")
	WebElement saveBtn;

	public boolean verifyContactsLabel() {
		return contactsLabel.isDisplayed();
	}

	public void createNewContact(String title, String firstName, String lastName, String company) {
		Select select = new Select(ntitle);
		select.selectByVisibleText(title);
		fName.sendKeys(firstName);
		lName.sendKeys(lastName);
		comp.sendKeys(company);
		//saveBtn.click();
	}
}
