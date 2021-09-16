package webdriver;

import java.util.concurrent.TimeUnit;

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

public class Topic_17_Wait_Part_V_Explicit {
	WebDriver driver;
	String Path = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	By startButtonBy = By.xpath("//button[text()='Start']");
	By loadingIcon = By.cssSelector("#loading");
	By helloWorldTextBy = By.xpath("//h4");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", Path + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		// driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	public void TC_01_Less() {
		explicitWait = new WebDriverWait(driver, 15);
		driver.get("https://automationfc.github.io/dynamic-loading/");

		explicitWait.until(ExpectedConditions.elementToBeClickable(startButtonBy));
		driver.findElement(startButtonBy).click();

		explicitWait = new WebDriverWait(driver, 3);

		// explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloWorldTextBy));
		Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");

	}

	public void TC_02_Equal() {
		explicitWait = new WebDriverWait(driver, 15);
		driver.get("https://automationfc.github.io/dynamic-loading/");

		explicitWait.until(ExpectedConditions.elementToBeClickable(startButtonBy));
		driver.findElement(startButtonBy).click();

		explicitWait = new WebDriverWait(driver, 5);

		// explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloWorldTextBy));

		Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");
	}

	public void TC_03_Greater() {
		explicitWait = new WebDriverWait(driver, 15);
		driver.get("https://automationfc.github.io/dynamic-loading/");

		explicitWait.until(ExpectedConditions.elementToBeClickable(startButtonBy));
		driver.findElement(startButtonBy).click();

		explicitWait = new WebDriverWait(driver, 8);

		// explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloWorldTextBy));

		Assert.assertEquals(driver.findElement(helloWorldTextBy).getText(), "Hello World!");
	}

	@Test
	public void TC_04_Demos() {
		explicitWait = new WebDriverWait(driver, 15);
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		driver.manage().window().maximize();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id = 'ctl00_ContentPlaceholder1_Label1']")));
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id = 'ctl00_ContentPlaceholder1_Label1']")).getText(), "No Selected Dates to display.");
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='1']")));
		driver.findElement(By.xpath("//a[text()='1']")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id = 'ctl00_ContentPlaceholder1_Label1']")).getText(), "Wednesday, September 1, 2021");
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']/a[text()='1']")).isDisplayed());
		
				
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
