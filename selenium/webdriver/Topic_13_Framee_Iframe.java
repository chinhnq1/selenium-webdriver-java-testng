package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Framee_Iframe {
	WebDriver driver;
	String Path = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", Path + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
	}

	@Test
	public void TC_01_Iframe() {
		driver.get("https://kyna.vn/");
		sleepInSecond(5);
		scrollBottomPage();
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));
		Assert.assertEquals(
				driver.findElement(By.xpath("//a[@title='Kyna.vn']//parent::div//following-sibling::div")).getText(),
				"168K lượt thích");
		driver.switchTo().defaultContent();

		driver.switchTo().frame(driver.findElement(By.cssSelector("#cs_chat_iframe")));
		driver.findElement(By.cssSelector("div.button_bar")).click();
		driver.findElement(By.cssSelector("div.bottom_medium")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("input.input_name+div.error_message")).getText(),
				"Tên của bạn chưa được nhập");
		Assert.assertEquals(driver.findElement(By.cssSelector("select#serviceSelect+div.error_message")).getText(),
				"Bạn chưa chọn dịch vụ hỗ trợ");
		driver.switchTo().defaultContent();
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector("button.search-button")).click();
		sleepInSecond(2);
		List<WebElement> listItem = driver.findElements(By.cssSelector("li.k-box-card h4"));
		for (WebElement item : listItem) {
			Assert.assertTrue(item.getText().contains("Excel"));
		}
		
	}

	@Test
	public void TC_02_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		driver.switchTo().frame("login_page");
		driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("Automation");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector("input#fldPasswordDispId")).isDisplayed());
		
		driver.findElement(By.xpath("//div[@class='footer-link-lrg']//a[text()='Terms and Conditions']")).click();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void scrollBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void sleepInSecond(long seconds) {

		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ie) {
		}
	}
}
