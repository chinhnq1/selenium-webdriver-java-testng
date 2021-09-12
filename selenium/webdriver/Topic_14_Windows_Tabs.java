package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Windows_Tabs {
	WebDriver driver;
	String Path = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", Path + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	// Get active current tab/ window
	// Get all tab and windows
	// Switch to tab or window
	@Test
	public void TC_01_Windows() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String parentID = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(1);

		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");

		driver.findElement(By.cssSelector("input#email")).sendKeys("child@gmail.com");

		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

		driver.findElement(By.cssSelector("input#email")).sendKeys("parent@gmail.com");

		sleepInSecond(1);

		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(1);

		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

		driver.findElement(By.xpath("//input[@data-view-id=\"main_search_form_input\"]")).sendKeys("tiki@gmail.com");
		
		closeAllWindowWthoutParent(parentID);
		
		driver.findElement(By.id("email")).sendKeys("After Close");
	}

	@Test
	public void TC_02_() {

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	// use for two window
	// compare ID
	// if different compare with parent > switch to
	public void switchToWindowByID(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String id : allWindows) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
			}
		}
	}

	// use for two or more windows
	// switch to each window
	// compare title
	// if same title > switch to and break
	public void switchToWindowByTitle(String expectedTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String id : allWindows) {
			driver.switchTo().window(id);
			String windowTitle = driver.getTitle();
			if (windowTitle.equals(expectedTitle)) {
				break;
			}
		}

	}

	public void closeAllWindowWthoutParent(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String id : allWindows) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			
		}
		driver.switchTo().window(parentID);

	}

	public void sleepInSecond(long seconds) {

		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ie) {
		}
	}
}
