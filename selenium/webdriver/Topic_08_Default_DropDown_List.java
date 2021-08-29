package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Default_DropDown_List {
	WebDriver driver;
	String Path = System.getProperty("user.dir");
	Select select;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", Path + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_NopCommerce() {

		String firstName, lastName, email, password, day, month, year, companyName;
		firstName = "Automation";
		lastName = "FC";
		email = "automation" + randomInt() + "@gmail.net";
		password = "12345678";
		day = "15";
		month = "December";
		year = "1999";
		companyName = "Automation FC";
		
		By genderMaleBy = By.id("gender-male");
		By firstNameBy = By.id("FirstName");
		By lastNameBy = By.id("LastName");
		By emailBy = By.id("Email");
		By companyNameBy = By.id("Company");

		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		driver.findElement(genderMaleBy).click();
		driver.findElement(firstNameBy).sendKeys(firstName);
		driver.findElement(lastNameBy).sendKeys(lastName);
		driver.findElement(emailBy).sendKeys(email);

		select = new Select(driver.findElement(By.name("DateOfBirthDay")));

		// select 1 item
		select.selectByVisibleText(day);		//**
		// de-select 1 item

		// kiem tra dropdown co phai multiple hay khong
		Assert.assertFalse(select.isMultiple());

		// Kiem tra item A da duoc chon dung hay chua
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);	//**

		// Get tong so item trong dropdown bang bao nhieu > verify xem no bang bao
		// nhieu?
		Assert.assertEquals(select.getOptions().size(), 32);

		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);

		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);

		driver.findElement(companyNameBy).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);

		driver.findElement(By.id("register-button")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class = 'result' and text()= 'Your registration completed']")).isDisplayed());
		
		driver.findElement(By.cssSelector("a.ico-account")).click();
		
		Assert.assertTrue(driver.findElement(genderMaleBy).isSelected());
		Assert.assertEquals(driver.findElement(firstNameBy).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(lastNameBy).getAttribute("value"), lastName);
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
		
		Assert.assertEquals(driver.findElement(emailBy).getAttribute("value"), email);
		Assert.assertEquals(driver.findElement(companyNameBy).getAttribute("value"), companyName);
	}
		
	
		
	@Test
	public void TC_02_Node() {
		driver.get("https://www.rode.com/wheretobuy");
		
		select = new Select(driver.findElement(By.id("where_country")));
		Assert.assertFalse(select.isMultiple());
		
		select.selectByVisibleText("Vietnam");
		
		driver.findElement(By.id("search_loc_submit")).click();
		
		
		// kho bi fail hon so voi check getText()
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='result_count']/span[text()='29']")).isDisplayed());
		
		List<WebElement> element = driver.findElements(By.xpath("//div[@class='result_item']//div[@class='store_name']"));
		
		Assert.assertEquals(element.size(),29);
		
		for (WebElement webElement : element) {
			System.out.println(webElement.getText());
		}
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomInt() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

}

// Summary
// select dropdown method; check isDisplayed instead of getText(); List<WebElement>
