package webdriver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_Part_VI_Mixing {
	WebDriver driver;
	String Path = System.getProperty("user.dir");
	WebDriverWait explicit;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", Path + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

	}

	public void TC_01_Found_Implicit_Explicit() {
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicit = new WebDriverWait(driver, 5);

		getDateNow();
		driver.findElement(By.cssSelector("input#email"));
		getDateNow();

		getDateNow();
		explicit.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
		getDateNow();

	}

	public void TC_02_1_Not_Found_Only_Implicit() {
		// apply for findElement and findElements
		// throw NoSuchElement Exception
		// Timeout = 5, internal = 0.5
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		getDateNow();
		try {
			driver.findElement(By.cssSelector("input#tiki"));
		} finally {
			getDateNow();
		}

	}

	public void TC_02_2_Not_Found_Only_Explicit() {
		// Throw Timeout Exception: wait for visible of Element
		// Timeout = 5s and interval = 0.5s
		driver.get("https://www.facebook.com/");
		explicit = new WebDriverWait(driver, 5);

		getDateNow();
		try {
			explicit.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#tiki")));
		} finally {
			getDateNow();
		}
	}

	@Test
	public void TC_03_Not_Found_Explicit_Implicit() {
		driver.get("https://www.facebook.com/");
		explicit = new WebDriverWait(driver, 5);
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

		getDateNow();
		try {
			explicit.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#tiki")));
		} finally {
			getDateNow();
		}

	}
	
	public void TC_03_Not_Found_Visibility_WebElement() {
		//thow NoSuchElement
		driver.get("https://www.facebook.com/");
		explicit = new WebDriverWait(driver, 8);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		getDateNow();
		try {
			explicit.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("input#tiki"))));
		} finally {
			getDateNow();
		}
		
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

	public void getDateNow() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		System.out.println(formatter.format(date));
	}
}
