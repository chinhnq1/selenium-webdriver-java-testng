package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Alert {
	WebDriver driver;
	String Path = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	Alert jsAlert;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", Path + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);

	}

	// @Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		// Wait for alert presence
		jsAlert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(jsAlert.getText(), "I am a JS Alert");
		jsAlert.accept();

	}

	// @Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		Alert jsAlert = driver.switchTo().alert();
		sleepInSecond(2);
		Assert.assertEquals(jsAlert.getText(), "I am a JS Confirm");
		jsAlert.dismiss();

		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@id = 'result' and text()='You clicked: Cancel']")).isDisplayed());
	}

	// @Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		Alert jsAlert = driver.switchTo().alert();
		sleepInSecond(2);
		Assert.assertEquals(jsAlert.getText(), "I am a JS prompt");
		jsAlert.sendKeys("daominhdam");
		jsAlert.accept();

		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@id = 'result' and text()='You entered: daominhdam']")).isDisplayed());

	}

	//@Test
	public void TC_04_Authentication_Alert() {
		// https://user:password@URL
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		Assert.assertTrue(driver
				.findElement(By.xpath("//p[contains(.,'Congratulations! You must have the proper credentials.')]"))
				.isDisplayed());
	}
	@Test
	public void TC_05_Authentication_Alert() {

		driver.get("http://the-internet.herokuapp.com/");
		String hrefVaue = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		passToURL(hrefVaue, "admin", "admin");
		Assert.assertTrue(driver
				.findElement(By.xpath("//p[contains(.,'Congratulations! You must have the proper credentials.')]"))
				.isDisplayed());
	}

	public void passToURL(String href, String user, String pass) {
		String [] hrefValue = href.split("//");
		href = hrefValue[0] + "//" + user +":"+ pass + "@" +  hrefValue[1];
		System.out.println(href);
		driver.get(href);
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
