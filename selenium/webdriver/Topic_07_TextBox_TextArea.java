package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_TextBox_TextArea {
	WebDriver driver;
	String Path = System.getProperty("user.dir");
	String userID, password, urlHomePage, name, gender, dateOfBirthInput, dateOfBirthOutput, addrInput, addrOutput,
			city, state, pin, phone, email;
	JavascriptExecutor jsExecutor;
	String customerID, editAddrInput, editCity, editState, editPin, editPhone, editEmail;

	By nameTextBoxBy = By.name("name");
	By genderRadioBy = By.xpath("//input[@value='f']");
	By dateOfBirthTextBoxBy = By.name("dob");
	By addrTextAreaBy = By.name("addr");
	By cityTextBoxBy = By.name("city");
	By stateTextBoxBy = By.name("state");
	By pinTextBoxBy = By.name("pinno");
	By phoneTextBoxBy = By.name("telephoneno");
	By emailTextBoxBy = By.name("emailid");
	By passTextBoxBy = By.name("password");
	By genderTextBoxBy = By.name("gender");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", Path + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// ep kieu tuong` minh
		jsExecutor = (JavascriptExecutor) driver;

		name = "John Stone";
		dateOfBirthInput = "01/01/1990";
		addrInput = "190 DC \nNew York";
		addrOutput = "190 DC New York";
		gender = "female";
		addrOutput = "190 DC New York";
		dateOfBirthOutput = "1990-01-01";
		city = "Hawaii";
		state = "LosAngle";
		pin = "123456";
		phone = "0987654321";
		email = "John" + randomInt() + "@gmail.us";

		editAddrInput = "201 DC OHIO";
		editCity = "New York";
		editState = "Ohio";
		editPin = "654323";
		editPhone = "0987665432";
		editEmail = "Stone" + randomInt() + "@hotmail.us";
	}

	public int randomInt() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	@Test
	public void TC_01_Register() {
		driver.get("http://demo.guru99.com/v4");
		urlHomePage = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();

		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		System.out.println(userID);
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

	}

	@Test
	public void TC_02_Login() {
		driver.get(urlHomePage);
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		Assert.assertTrue(driver
				.findElement(By.xpath(
						"//marquee[@class= 'heading3' and text() = \"Welcome To Manager's Page of Guru99 Bank\"]"))
				.isDisplayed());

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

	}

	@Test
	public void TC_03_New_Customer() {
		driver.findElement(nameTextBoxBy).sendKeys(name);
		driver.findElement(genderRadioBy).click();

		jsExecutor.executeScript("arguments[0].removeAttribute('type')", driver.findElement(dateOfBirthTextBoxBy));

		driver.findElement(dateOfBirthTextBoxBy).sendKeys(dateOfBirthInput);
		driver.findElement(addrTextAreaBy).sendKeys(addrInput);
		driver.findElement(cityTextBoxBy).sendKeys(city);
		driver.findElement(stateTextBoxBy).sendKeys(state);
		driver.findElement(pinTextBoxBy).sendKeys(pin);
		driver.findElement(phoneTextBoxBy).sendKeys(phone);
		driver.findElement(emailTextBoxBy).sendKeys(email);
		driver.findElement(passTextBoxBy).sendKeys(password);

		driver.findElement(By.name("sub")).click();
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println(customerID);
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']"))
						.isDisplayed());
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),
				gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				dateOfBirthOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				addrOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				email);

	}

	 @Test
	public void TC_04_Edit_Customer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		// Verify after clicking edit button
		Assert.assertEquals(driver.findElement(By.name("name")).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(By.name("gender")).getAttribute("value"), gender);
		Assert.assertEquals(driver.findElement(By.name("dob")).getAttribute("value"), dateOfBirthOutput);
		Assert.assertEquals(driver.findElement(By.name("addr")).getAttribute("value"), addrInput);
		Assert.assertEquals(driver.findElement(By.name("city")).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(By.name("state")).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(By.name("pinno")).getAttribute("value"), pin);
		Assert.assertEquals(driver.findElement(By.name("telephoneno")).getAttribute("value"), phone);
		Assert.assertEquals(driver.findElement(By.name("emailid")).getAttribute("value"), email);

		// Editting button
		driver.findElement(By.name("addr")).clear();
		driver.findElement(By.name("addr")).sendKeys(editAddrInput);
		driver.findElement(By.name("city")).clear();
		driver.findElement(By.name("city")).sendKeys(editCity);
		driver.findElement(By.name("state")).clear();
		driver.findElement(By.name("state")).sendKeys(editState);
		driver.findElement(By.name("pinno")).clear();
		driver.findElement(By.name("pinno")).sendKeys(editPin);
		driver.findElement(By.name("telephoneno")).clear();
		driver.findElement(By.name("telephoneno")).sendKeys(editPhone);
		driver.findElement(By.name("emailid")).clear();
		driver.findElement(By.name("emailid")).sendKeys(editEmail);

		driver.findElement(By.name("sub")).click();

		// verify after editting infor

		Assert.assertTrue(driver
				.findElement(By.xpath("//p[@class='heading3' and text()='Customer details updated Successfully!!!']"))
				.isDisplayed());

		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText(), customerID);
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),
				gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				dateOfBirthOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				editAddrInput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				editEmail);

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}
