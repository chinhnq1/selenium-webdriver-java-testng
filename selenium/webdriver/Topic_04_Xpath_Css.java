package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Css {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_Login_Empty_Email_Password() {

		driver.findElement(By.id("send2")).click();
		Assert.assertEquals("This is a required field.",
				driver.findElement(By.id("advice-required-entry-email")).getText());
		Assert.assertEquals("This is a required field.",
				driver.findElement(By.id("advice-required-entry-pass")).getText());

	}

	@Test
	public void TC_02_Login_Invalid_Email() {

		driver.navigate().refresh();
		driver.findElement(By.name("login[username]")).sendKeys("12341234@1234");
		driver.findElement(By.name("login[password]")).sendKeys("12341256");

		driver.findElement(By.id("send2")).click();
		Assert.assertEquals("Please enter a valid email address. For example johndoe@domain.com.",
				driver.findElement(By.id("advice-validate-email-email")).getText());
	}

	@Test
	public void TC_03_Login_Invalid_Password() {
		driver.navigate().refresh();
		driver.findElement(By.name("login[username]")).sendKeys("automation@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123");

		driver.findElement(By.id("send2")).click();
		Assert.assertEquals("Please enter 6 or more characters without leading or trailing spaces.",
				driver.findElement(By.id("advice-validate-password-pass")).getText());
	}

	@Test
	public void TC_04_Login_Incorrect_Email() {

		driver.navigate().refresh();
		driver.findElement(By.name("login[username]")).sendKeys("automation@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123456");

		driver.findElement(By.id("send2")).click();
		Assert.assertEquals("Invalid login or password.",
				driver.findElement(By.xpath("//li [@class='error-msg']//span")).getText());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
