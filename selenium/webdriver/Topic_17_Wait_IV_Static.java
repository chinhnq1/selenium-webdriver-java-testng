package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
// dung sleep (static wait) thay cho implicit wait
public class Topic_17_Wait_IV_Static {
	WebDriver driver;
	String Path = System.getProperty("user.dir");
	By startButtonBy  = By.xpath("//button[text()='Start']");
	By loadingIcon  = By.cssSelector("#loading");
	By helloWorldTextBy = By.xpath("//h4");
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", Path + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
	}
	
	@Test
	public void TC_01_Less_Than() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(startButtonBy).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");
	}

	@Test
	public void TC_02_Enough() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(startButtonBy).click();
		sleepInSecond(5);

		Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");		
	}
	
	@Test
	public void TC_03_Greater_Than() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(startButtonBy).click();
		sleepInSecond(8);

		Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");
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
