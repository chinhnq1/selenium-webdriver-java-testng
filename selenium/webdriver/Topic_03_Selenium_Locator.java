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

public class Topic_03_Selenium_Locator {
	// Khai bao 1 bien dai dien cho Selenium WebDriver
	WebDriver driver;
	String Path = System.getProperty("user.dir");	
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", Path + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		// Set timeout de tim element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		// Mo application len (AUT/SUT) Alt + Up
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}
	
//	@Test
//	public void TC_01_FindElement() {
//		
//		// Single element: WebElement
//		driver.findElement(By.className("")).click();
//		
//		// findElement: tim Element
//		// By.xxx: vs locator nao
//		// Action len Element: click, sendkey, getText
//		
//		
//		// Multiple Element: List <Web Element>
//		List<WebElement> buttons = driver.findElements(By.className(""));
//		buttons.get(0);
//			
//	}
	@Test
	public void TC_02_Locator() {
		// Selenium Locator
		driver.findElement(By.id("send2")).click();
		
		// Verify error message appear
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
				
	}
	
	@Test
	public void TC_03_Class() {
		driver.navigate().refresh();
		
		driver.findElement(By.className("validate-password")).sendKeys("123456789");
				
	}
	
	
	@Test
	public void TC_04_Name() {
		driver.navigate().refresh();
		
		driver.findElement(By.name("send")).click();
		
		// Verify error message appear
		
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
	}
	
	@Test
	public void TC_05_TagName() {
		driver.navigate().refresh();
		
		// Lay het tat ca duong link
		List<WebElement> loginPageLinks = driver.findElements(By.tagName("a"));
		for (WebElement webElement : loginPageLinks) {
			System.out.println(webElement.getText());
		}
				
	}
	
	@Test
	public void TC_06_LinkText() {
		driver.navigate().refresh();
		driver.findElement(By.linkText("Forgot Your Password?")).click();
		
		// Verify Textbox with id email_address
		Assert.assertTrue(driver.findElement(By.id("email_address")).isDisplayed());

	}
	@Test
	public void TC_07_Class() {

		driver.findElement(By.partialLinkText("Back to")).click();
		Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());
		
		
	}
	@Test
	public void TC_08_Css() {
		driver.findElement(By.cssSelector("#email")).sendKeys("chinh@gmail.com");	
		driver.findElement(By.cssSelector("input[name='login[password]']")).sendKeys("12345678");
		
	}
	
	@Test
	public void TC_09_Xpath() {
		driver.navigate().refresh();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("chin@gmail.com");	
		driver.findElement(By.xpath("//input[@title='Password']")).sendKeys("12345678");
		
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}












