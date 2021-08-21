package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

public class Topic_04_Xpath_Css_II {
	WebDriver driver;
	String name, emailAddress, password, phone;

	// Action
	By nameTextBoxBy = By.id("txtFirstname");
	By emailTextBoxBy = By.id("txtEmail");
	By confirmEmailTextBoxBy = By.id("txtCEmail");
	By passTextBoxBy = By.id("txtPassword");
	By confirmPassTextBoxBy = By.id("txtCPassword");
	By phoneTextBoxBy = By.id("txtPhone");
	By registerButtonBy = By.xpath("//div[@class='form frmRegister']//button");

	// Error
	By nameErrorMsgBy = By.id("txtFirstname-error");
	By emailErrorMsgBy = By.id("txtEmail-error");
	By confirmEmailErrorMsgBy = By.id("txtCEmail-error");
	By passErrorMsgBy = By.id("txtPassword-error");
	By confirmPassErrorMsgBy = By.id("txtCPassword-error");
	By phoneErrorMsgBy = By.id("txtPhone-error");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		name = "John Stone";
		emailAddress = "automation@gmail.com";
		password = "123456";
		phone = "0987654321";

	}

	@BeforeMethod
	public void beforeMehthod() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_01_Empty() {
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(nameErrorMsgBy).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(emailErrorMsgBy).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(confirmEmailErrorMsgBy).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(passErrorMsgBy).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(confirmPassErrorMsgBy).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Vui lòng nhập số điện thoại.");
	}

	@Test
	public void TC_02_Invalid_Email() {
		driver.findElement(nameTextBoxBy).sendKeys(name);
		driver.findElement(emailTextBoxBy).sendKeys("123@123@");
		driver.findElement(confirmEmailTextBoxBy).sendKeys(emailAddress);
		driver.findElement(passTextBoxBy).sendKeys(password);
		driver.findElement(confirmPassTextBoxBy).sendKeys(password);
		driver.findElement(phoneTextBoxBy).sendKeys(phone);
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(emailErrorMsgBy).getText(), "Vui lòng nhập email hợp lệ");

	}

	@Test
	public void TC_03_Incorrect_Confirm_Email() {
		// Email nhap khong dung
		driver.findElement(nameTextBoxBy).sendKeys(name);
		driver.findElement(emailTextBoxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextBoxBy).sendKeys("automatino@gmail.com");
		driver.findElement(passTextBoxBy).sendKeys(password);
		driver.findElement(confirmPassTextBoxBy).sendKeys(password);
		driver.findElement(phoneTextBoxBy).sendKeys(phone);
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(confirmEmailErrorMsgBy).getText(), "Email nhập lại không đúng");

	}

	@Test
	public void TC_04_Password_Less_Than_6_Character() {
		// Mat khau nho hon 6 ki tu
		driver.findElement(nameTextBoxBy).sendKeys(name);
		driver.findElement(emailTextBoxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextBoxBy).sendKeys(emailAddress);
		driver.findElement(passTextBoxBy).sendKeys("123");
		driver.findElement(confirmPassTextBoxBy).sendKeys("123");
		driver.findElement(phoneTextBoxBy).sendKeys(phone);
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(passErrorMsgBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(confirmPassErrorMsgBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");

	}
	@Test
	public void TC_05_Incorrect_Confirm_Password() {
		// Mat khau nho hon 6 ki tu
		driver.findElement(nameTextBoxBy).sendKeys(name);
		driver.findElement(emailTextBoxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextBoxBy).sendKeys(emailAddress);
		driver.findElement(passTextBoxBy).sendKeys(password);
		driver.findElement(confirmPassTextBoxBy).sendKeys("654321");
		driver.findElement(phoneTextBoxBy).sendKeys(phone);
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(confirmPassErrorMsgBy).getText(), "Mật khẩu bạn nhập không khớp");

	}
	@Test
	public void TC_06_Invalid_Phone() {
		// Vui long nhap so
		driver.findElement(nameTextBoxBy).sendKeys(name);
		driver.findElement(emailTextBoxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextBoxBy).sendKeys(emailAddress);
		driver.findElement(passTextBoxBy).sendKeys(password);
		driver.findElement(confirmPassTextBoxBy).sendKeys(password);
		driver.findElement(phoneTextBoxBy).sendKeys("emailAddress");
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Vui lòng nhập con số");

		// 098765
		// So dt phai tu 10-11 so

		driver.findElement(phoneTextBoxBy).clear();
		driver.findElement(phoneTextBoxBy).sendKeys("098765");
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Số điện thoại phải từ 10-11 số.");
		

		
		// 0987654321234
		// So dt tu 10-11 so
		driver.findElement(phoneTextBoxBy).clear();
		driver.findElement(phoneTextBoxBy).sendKeys("0987654321234");
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Số điện thoại phải từ 10-11 số.");
		
		// 123
		// Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019
		driver.findElement(phoneTextBoxBy).clear();
		driver.findElement(phoneTextBoxBy).sendKeys("123");
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
				
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
