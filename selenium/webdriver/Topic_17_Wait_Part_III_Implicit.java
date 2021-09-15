package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_Part_III_Implicit {
	WebDriver driver;
	String Path = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", Path + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC_01_Less_Than() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//h4")).getText(), "Hello World!");
	}

	@Test
	public void TC_02_Enough() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//h4")).getText(), "Hello World!");		
	}
	
	@Test
	public void TC_03_Greater_Than() {
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//h4")).getText(), "Hello World!");
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
