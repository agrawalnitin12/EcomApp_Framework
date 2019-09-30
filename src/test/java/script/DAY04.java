package script;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import reusable.BusinessFunctions;

public class DAY04 {
	
	@Test
	public void TC04() throws Exception {
		System.setProperty("webdriver.gecko.driver", "D:\\Set Up Files\\Selenium\\geckodriver-v0.24.0-win64\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		BusinessFunctions functions = new BusinessFunctions(driver);
		functions.navigateToApp();
		functions.clickMobile();
		functions.compareProducts();
		functions.closeBrowser();
	}

}
