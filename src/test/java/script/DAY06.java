package script;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import reusable.BusinessFunctions;

public class DAY06 {

	@Test
	public void TC06() throws Exception {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		BusinessFunctions functions = new BusinessFunctions(driver);
		functions.navigateToApp();
		functions.purchaseProduct();
		functions.closeBrowser();
		
	}
	
}
