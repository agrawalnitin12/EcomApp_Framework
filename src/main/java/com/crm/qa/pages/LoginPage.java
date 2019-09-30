package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	// Page Factory - OR;
	@FindBy(name = "username")
	WebElement uname;

	@FindBy(name = "password")
	WebElement pwd;

	@FindBy(xpath = "//input[@type='submit']")
	@CacheLookup
	WebElement loginBtn;

	@FindBy(xpath = "//a[contains(text(),'Sign Up')]")
	WebElement signUpLink;

	@FindBy(xpath = "//a[@class=\"navbar-brand\"]/img")
	WebElement crmLogo;

	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public boolean validateCRMImage() {
		return crmLogo.isDisplayed();
	}

	public HomePage login(String un, String pswd) throws InterruptedException {
		uname.sendKeys(un);
		pwd.sendKeys(pswd);
		Thread.sleep(3000);
		loginBtn.click();
		return new HomePage();
	}

}
