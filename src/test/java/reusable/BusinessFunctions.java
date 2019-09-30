package reusable;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import objectRepository.MobilePage;

public class BusinessFunctions {
	WebDriver driver;
	
	public BusinessFunctions(WebDriver driver) {
		this.driver = driver;
	}
	
	public void navigateToApp() {
		driver.get("http://live.guru99.com/index.php/");
	}
	
	public void closeBrowser() {
		driver.quit();
	}
	
	public void verifyHomeTitle() {
		String pageTitle = driver.findElement(MobilePage.txtHome).getText();
		Assert.assertTrue(pageTitle.contains("THIS IS DEMO SITE"));
	}
	
	public void clickMobile() {
		driver.findElement(MobilePage.lnkMobile).click();
	}
	
	public void verifyMobileTitle() {
		String mobileTitle = driver.findElement(MobilePage.txtMobile).getText();
		Assert.assertEquals(mobileTitle, "MOBILE");
	}
	
	public void mobileSort() {
		Select dropDown = new Select(driver.findElement(MobilePage.ddMobile));
		dropDown.selectByVisibleText("Name");
	}
	
	public void captureScreenshot(String screenShotname) {
		try {
			File screenShotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(screenShotFile, new File("./Screenshots/"+screenShotname+".png"));
			System.out.println("screen shot captured");
		} catch (Exception e) {
			System.out.println("Exception with taking screenshot"+e.getMessage());
		}
	}
		
	public void compareSonyPrice() {
		String price = driver.findElement(MobilePage.txtSonyPrice).getText();
		driver.findElement(MobilePage.lnkSony).click();
		String newPrice = driver.findElement(MobilePage.txtSonyPrice).getText();
		Assert.assertEquals(price, newPrice);
		}
	
	public void errorVerification() {
		driver.findElement(MobilePage.btnAddtoCart).click();
		driver.findElement(MobilePage.lnkEdit).click();
		driver.findElement(MobilePage.txtQty).clear();
		driver.findElement(MobilePage.txtQty).sendKeys("1000");
		driver.findElement(MobilePage.btnUpdate).click();
		String error = driver.findElement(MobilePage.txtError).getText();
		Assert.assertEquals(error, "The maximum quantity allowed for purchase is 500.");
		driver.findElement(MobilePage.lnkEmptyCart).click();
		String msgTitle = driver.findElement(MobilePage.txtTitle).getText();
		Assert.assertEquals(msgTitle, "SHOPPING CART IS EMPTY");
	}
	
	public void compareProducts() throws Exception {
		String mainMob1 = driver.findElement(MobilePage.txtSony).getText();
		driver.findElement(MobilePage.lnkSonyCom).click();
		String mainMob2 = driver.findElement(MobilePage.txtIPhone).getText();
		driver.findElement(MobilePage.lnkIPHCom).click();
		driver.findElement(MobilePage.btnCompare).click();
		String parent = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		for(String child:allWindows) {
			if(!parent.equalsIgnoreCase(child)) {
				driver.switchTo().window(child);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				String popupMobile1 = driver.findElement(MobilePage.txtSonyPopup).getText();
				String popupMobile2 = driver.findElement(MobilePage.txtIphPopup).getText();
				String subHead = driver.findElement(MobilePage.txtCompare).getText();
				Assert.assertTrue(subHead.contains("COMPARE PRODUCTS"));
				Assert.assertEquals(mainMob1, popupMobile1);
				Assert.assertEquals(mainMob2, popupMobile2);
				driver.findElement(MobilePage.btnClose).click();
			}
		}
		driver.switchTo().window(parent);
	}
	public void createAccount() {
		driver.findElement(MobilePage.lnkMyaccount).click();
		driver.findElement(MobilePage.btnCreateAC).click();
		driver.findElement(MobilePage.txtFirstname).sendKeys("Nitin");
		driver.findElement(MobilePage.txtLastName).sendKeys("Agrawal");
		driver.findElement(MobilePage.txtEmailID).sendKeys("nitin@ab.com");
		driver.findElement(MobilePage.txtPwd).sendKeys("nitin123");
		driver.findElement(MobilePage.txtCnfPwd).sendKeys("nitin123");
		driver.findElement(MobilePage.chkNewsletter).click();
		driver.findElement(MobilePage.btnRegister).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, "http://live.guru99.com/index.php/customer/account/index/");
		driver.findElement(MobilePage.lnkTV).click();
		driver.findElement(MobilePage.lnkWishList).click();
		driver.findElement(MobilePage.btnWishList).click();
		driver.findElement(MobilePage.txtEID).sendKeys("nitin123@gmail.com");
		driver.findElement(MobilePage.txtMsg).sendKeys("Please buy this product");
		driver.findElement(MobilePage.btnWishList).click();
		String wishListSuccess = driver.findElement(MobilePage.txtSuccessMsg).getText();
		Assert.assertEquals(wishListSuccess, "Your Wishlist has been shared.");
	}
	
	public void purchaseProduct() throws Exception {
		driver.findElement(MobilePage.lnkMyaccount).click();
		driver.findElement(MobilePage.txtEnterEmail).sendKeys("abcd@123.com");
		driver.findElement(MobilePage.txtEnterPwd).sendKeys("abcd1234");
		driver.findElement(MobilePage.btnLogin).click();
		driver.findElement(MobilePage.lnkMyWish).click();
		driver.findElement(MobilePage.btnAddcart).click();
		Select ddShip = new Select(driver.findElement(MobilePage.ddSProvince));
		ddShip.selectByVisibleText("New York");
		driver.findElement(MobilePage.txtSZip).sendKeys("542896");
		driver.findElement(MobilePage.btnEstimate).click();
		Assert.assertTrue(driver.findElement(MobilePage.radioFlat).isDisplayed());
		driver.findElement(MobilePage.radioFlat).click();
		driver.findElement(MobilePage.btnUpdateTotal).click();
		String cartPrice = driver.findElement(MobilePage.txtCartPrice).getText();
		String flatPrice = driver.findElement(MobilePage.txtFlatPrice).getText();
		String grandTotal = driver.findElement(MobilePage.txtTotalPrice).getText();
		double newCartPrice = Double.parseDouble(cartPrice.substring(1));
		double newFlatPrice = Double.parseDouble(flatPrice.substring(1));
		double newGrandTotal = Double.parseDouble(grandTotal.substring(1));
		double priceBefore = newCartPrice + newFlatPrice;
		Assert.assertEquals(newGrandTotal, priceBefore);
		driver.findElement(MobilePage.btnCheckout).click();
//		driver.findElement(MobilePage.txtAddress).clear();
//		driver.findElement(MobilePage.txtAddress).sendKeys("ABC");
//		driver.findElement(MobilePage.txtCity).clear();
//		driver.findElement(MobilePage.txtCity).sendKeys("New York");
//		dropDown=driver.findElement(MobilePage.ddProvice);
//		Select dProvince = new Select(dropDown);
//		dProvince.selectByVisibleText("New York");
//		driver.findElement(MobilePage.txtZip).clear();
//		driver.findElement(MobilePage.txtZip).sendKeys("542896");
//		driver.findElement(MobilePage.txtTelephone).clear();
//		driver.findElement(MobilePage.txtTelephone).sendKeys("12345678");
//		
//		dropDown = driver.findElement(MobilePage.ddCountry);
//		Select dCountry = new Select(dropDown);
//		dCountry.selectByVisibleText("United States");
		Thread.sleep(3000);
		driver.findElement(MobilePage.btnContinue).click();
		Thread.sleep(3000);
		driver.findElement(MobilePage.btnSContinue).click();
		Thread.sleep(3000);
		driver.findElement(MobilePage.radioPayment).click();
		Thread.sleep(3000);
		driver.findElement(MobilePage.btnPContinue).click();
		Thread.sleep(3000);
		driver.findElement(MobilePage.btnPlaceOrder).click();
		Thread.sleep(5000);
		String orderNumber = driver.findElement(MobilePage.txtOrderNumber).getText();
		System.out.println("Your Order Number is: "+orderNumber);
	}
	
		public void orderPrint() {
			driver.findElement(MobilePage.lnkMyaccount).click();
			driver.findElement(MobilePage.txtEnterEmail).sendKeys("abcd@123.com");
			driver.findElement(MobilePage.txtEnterPwd).sendKeys("abcd1234");
			driver.findElement(MobilePage.btnLogin).click();
			driver.findElement(MobilePage.lnkMyOrder).click();
			WebElement table = driver.findElement(By.tagName("table"));
			String order_Num = table.findElement(MobilePage.txtOrderNo).getText();
			String order_Status = table.findElement(MobilePage.txtOrderStatus).getText();
			Assert.assertEquals(order_Num, "100009497");
			Assert.assertEquals(order_Status, "Pending");
			table.findElement(MobilePage.txtOrder).click();
			String parent = driver.getWindowHandle();
			driver.findElement(MobilePage.printOrder).click();
			Set<String> allWindows   = driver.getWindowHandles();
			for (String child : allWindows)
			{
				if(!parent.equalsIgnoreCase(child))
				{
					driver.switchTo().window(child);
					driver.close();
					System.out.println("print successful");
				}
			}
			driver.switchTo().window(parent);
}
		
		public void reOrder() throws Exception {
			driver.findElement(MobilePage.lnkMyaccount).click();
			driver.findElement(MobilePage.txtEnterEmail).sendKeys("abcd@123.com");
			driver.findElement(MobilePage.txtEnterPwd).sendKeys("abcd1234");
			driver.findElement(MobilePage.btnLogin).click();
			driver.findElement(MobilePage.reOrder).click();
			String gTotalBefore = driver.findElement(MobilePage.grandTotal).getText();
			driver.findElement(MobilePage.lnkEdit).click();
			driver.findElement(MobilePage.txtQty).clear();
			driver.findElement(MobilePage.txtQty).sendKeys("10");
			driver.findElement(MobilePage.btnUpdate).click();
			String gTotalAfter = driver.findElement(MobilePage.grandTotal).getText();
			Assert.assertNotEquals(gTotalBefore, gTotalAfter);
			driver.findElement(MobilePage.btnCheckout).click();
			driver.findElement(MobilePage.btnContinue).click();
			Thread.sleep(3000);
			driver.findElement(MobilePage.btnSContinue).click();
			Thread.sleep(3000);
			driver.findElement(MobilePage.radioPayment).click();
			Thread.sleep(3000);
			driver.findElement(MobilePage.btnPContinue).click();
			Thread.sleep(3000);
			driver.findElement(MobilePage.btnPlaceOrder).click();
			Thread.sleep(5000);
			String orderNumber = driver.findElement(MobilePage.txtOrderNumber).getText();
			System.out.println("Your Order Number is: "+orderNumber);
		}
		
		public void discount() {
			driver.findElement(MobilePage.btnAddIphone).click();
			driver.findElement(MobilePage.txtDiscount).sendKeys("GURU50");
			driver.findElement(MobilePage.lnkApply).click();
			String total = driver.findElement(MobilePage.txtGrandTotalDis).getText();
			String discount = driver.findElement(MobilePage.txtTotalDis).getText();
			double newTotal = Double.parseDouble(total.substring(1));
			double newDiscount = Double.parseDouble(discount.substring(2));
			double dis = (newTotal/100)*5;
			Assert.assertEquals(newDiscount, dis);
		}
		
		public void orderEmail() {
			driver.get("http://live.guru99.com/index.php/backendlogin");
			driver.findElement(MobilePage.txtUserback).sendKeys("user01");
			driver.findElement(MobilePage.txtPwdback).sendKeys("guru99com");
			driver.findElement(MobilePage.btnLoginback).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement window = driver.findElement(MobilePage.winPopup);
			window.findElement(MobilePage.closePopup).click();
			Actions act = new Actions(driver);
			WebElement ele = driver.findElement(MobilePage.lnkBackSales);
			act.moveToElement(ele).perform();
			driver.findElement(MobilePage.lnkBackOrder).click();
			WebElement dropDown = driver.findElement(MobilePage.ddCSV);
			Select ddCSV = new Select(dropDown);
			ddCSV.selectByVisibleText("CSV");
			driver.findElement(MobilePage.btnExport).click();
		}
}
