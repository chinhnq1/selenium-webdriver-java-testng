package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Commands_II {
	WebDriver driver;
	String Path = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", Path + "\\browserDrivers\\geckodriver.exe");
		// System.setProperty("webdriver.chrome.driver",
		// ".\\browserDrivers\\chromedriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Url() {
		driver.get("https://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
		String loginUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginUrl, "http://live.demoguru99.com/index.php/customer/account/login/");

		driver.findElement(By.xpath("//div[@class='buttons-set']//span[text()='Create an Account']")).click();
		String accountUrl = driver.getCurrentUrl();
		Assert.assertEquals(accountUrl, "http://live.demoguru99.com/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_Title() {
		driver.get("https://live.demoguru99.com/");

		driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Customer Login");

		driver.findElement(By.xpath("//div[@class='buttons-set']//span[text()='Create an Account']")).click();
		String accountPageTitle = driver.getTitle();
		Assert.assertEquals(accountPageTitle, "Create New Customer Account");
	}

	@Test
	public void TC_03_Navigation() {
		driver.get("https://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//div[@class='buttons-set']//span[text()='Create an Account']")).click();
		String accountPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(accountPageUrl, "http://live.demoguru99.com/index.php/customer/account/create/");

		driver.navigate().back();
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "http://live.demoguru99.com/index.php/customer/account/login/");

		driver.navigate().forward();
		String accountPageTitle = driver.getTitle();
		Assert.assertEquals(accountPageTitle, "Create New Customer Account");

	}

	@Test
	public void TC_04_Page_Source() {
		driver.get("https://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
		String loginPageSource = driver.getPageSource();
		Assert.assertTrue(loginPageSource.contains("Login or Create an Account"));
		
		driver.findElement(By.xpath("//div[@class='buttons-set']//span[text()='Create an Account']")).click();
		String accountPageSource = driver.getPageSource();
		Assert.assertTrue(accountPageSource.contains("Create an Account"));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
