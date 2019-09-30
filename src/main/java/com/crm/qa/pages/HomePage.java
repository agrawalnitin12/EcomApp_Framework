package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {

	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//td[contains(text(),'User: Nitin Agrawal')]")
	WebElement userNameLabel;
	
	@FindBy(xpath = "//a[contains(text(),'Contacts')]")
	WebElement contactLink;
	
	@FindBy(xpath="//a[contains(text(),'New Contact')]")
	WebElement newContactLink;

	@FindBy(xpath = "//a[contains(text(),'Deals')]")
	WebElement dealLink;

	@FindBy(xpath = "//a[contains(text(),'Tasks')]")
	WebElement taskLink;

	public String validateTitle() {
		return driver.getTitle();
	}
	
	public boolean verifyUserName() {
		return userNameLabel.isDisplayed();
	}

	public ContactsPage clickOnContacts() {
		contactLink.click();
		return new ContactsPage();
	}
	
	public void clickOnNewContactLink() {
		Actions action = new Actions(driver);
		action.moveToElement(contactLink).build().perform();
		newContactLink.click();
	}

	public DealsPage clickOnDeals() {
		dealLink.click();
		return new DealsPage();
	}
	
	public TasksPage clickOnTasks() {
		taskLink.click();
		return new TasksPage();
	}

}
