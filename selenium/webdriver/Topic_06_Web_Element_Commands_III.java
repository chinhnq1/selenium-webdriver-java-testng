package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Commands_III {
	WebDriver driver;
	String Path = System.getProperty("user.dir");
	By passTextBoxBy = By.id("new_password");
	By createButton = By.id("create-account");

	String firstName, lastName, fullName, email, password;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", Path + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		firstName = "Automation";
		lastName = "FC";
		email = firstName + randomInt() + "@gmail.uk";
		password = "123456";
		fullName = firstName + " " + lastName;

	}

	public int randomInt() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	// @Test
	public void TC_01_Signup_Validate() {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.id("email")).sendKeys("Automation@gmail.com");
		driver.findElement(By.id("new_username")).sendKeys("Automation");
		driver.findElement(By.id("marketing_newsletter")).click();

		// Lowercase
		driver.findElement(passTextBoxBy).sendKeys("abc");
		Assert.assertTrue(driver
				.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']"))
				.isDisplayed());
		Assert.assertFalse(driver.findElement(createButton).isEnabled());

		// Uppercase
		driver.findElement(passTextBoxBy).clear();
		driver.findElement(passTextBoxBy).sendKeys("ABC");
		Assert.assertTrue(driver
				.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']"))
				.isDisplayed());
		Assert.assertFalse(driver.findElement(createButton).isEnabled());

		// Number
		driver.findElement(passTextBoxBy).clear();
		driver.findElement(passTextBoxBy).sendKeys("123");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed' and text()='One number']"))
				.isDisplayed());
		Assert.assertFalse(driver.findElement(createButton).isEnabled());

		// special character
		driver.findElement(passTextBoxBy).clear();
		driver.findElement(passTextBoxBy).sendKeys("!!!");
		Assert.assertTrue(
				driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']"))
						.isDisplayed());
		Assert.assertFalse(driver.findElement(createButton).isEnabled());

		// Over 8 character
		driver.findElement(passTextBoxBy).clear();
		driver.findElement(passTextBoxBy).sendKeys("😍😍😍😍");
		Assert.assertTrue(
				driver.findElement(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']"))
						.isDisplayed());
		Assert.assertFalse(driver.findElement(createButton).isEnabled());

		// Full
		driver.findElement(passTextBoxBy).clear();
		driver.findElement(passTextBoxBy).sendKeys("Abcd1234@");
		Assert.assertFalse(driver
				.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']"))
				.isDisplayed());
		Assert.assertFalse(driver
				.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']"))
				.isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed' and text()='One number']"))
				.isDisplayed());
		Assert.assertFalse(
				driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']"))
						.isDisplayed());
		Assert.assertFalse(
				driver.findElement(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']"))
						.isDisplayed());
		Assert.assertTrue(driver.findElement(createButton).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("marketing_newsletter")).isSelected());

	}

	@Test
	public void TC_02_Guru99_Account() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);

		driver.findElement(By.xpath("//span[text()='Register']")).click();

		Assert.assertTrue(driver.findElement(By
				.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.']"))
				.isDisplayed());

		// cach 1

		String contactInformation = driver.findElement(By
				.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p"))
				.getText();
		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(email));

		// cach 2
//		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p[contains(.,'"+ fullName +"')]")).isDisplayed());
//		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p[contains(.,'"+ email +"')]")).isDisplayed());	
		driver.findElement(By.xpath("//a/span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
 
	}

	@Test
	public void TC_03_Guru99_Account() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.id("send2")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
		String contactInformation = driver.findElement(By
				.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p"))
				.getText();
		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(email));		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
