package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Popup {
	WebDriver driver;
	String Path = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", Path + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	// @Test
	public void TC_01_Fixed_PopUp() {
		driver.get("https://ngoaingu24h.vn/");
		driver.findElement(By.cssSelector("button.icon-before")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("div#modal-login-v1>div")).isDisplayed());

		driver.findElement(By.cssSelector("input#account-input")).clear();
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("AutomationFC");
		driver.findElement(By.cssSelector("input#password-input")).clear();
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("AutomationFC");
		driver.findElement(By.cssSelector("button.btn-v1.btn-login-v1.buttonLoading")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(),
				"Tài khoản không tồn tại!");
		driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
		Assert.assertFalse(driver.findElement(By.cssSelector("div#modal-login-v1>div")).isDisplayed());

	}

	@Test
	public void TC_02_Random_In_DOM() {
		driver.get("https://blog.testproject.io/");
		WebElement popUp = driver.findElement(By.cssSelector("div#mailch-bg>div"));
		if (popUp.isDisplayed()) {
			System.out.println("Pop up is displayed.");
			driver.findElement(By.cssSelector("div#close-mailch")).click();
		} else {
			System.out.println("Pop up is not displayed.");
		}
		driver.findElement(By.cssSelector("#search-2 input.search-field")).sendKeys("Selenium");
		sleepInSecond(1);
		driver.findElement(By.cssSelector("#search-2 span.glass")).click();
		List<WebElement> articleTitle = driver.findElements(By.cssSelector("h3.post-title>a"));
		System.out.println(articleTitle.size());
		for (WebElement article : articleTitle) {
			Assert.assertTrue(article.getText().contains("Selenium"));
		}
	}

	@Test
	public void TC_03_Random_Not_In_DOM() {
		driver.get("https://shopee.vn/");
		sleepInSecond(8);
		// List Element return null if unable to locate element
		List<WebElement> popUp = driver.findElements(By.cssSelector("div.shopee-popup__container img"));
		// check size of list element > 0 to make sure have popup 
		if (popUp.size() >0 && popUp.get(0).isDisplayed()) {
			System.out.println("Pop up is displayed.");
			driver.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
		} else {
			System.out.println("Pop up is not displayed.");
		}
		driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("Mac Pro");
		driver.findElement(By.cssSelector("button.btn--inline")).click();
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long seconds) {

		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ie) {
		}
	}
}
