package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Commands_I {
	WebDriver driver;
	String Path = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", Path + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_() {
		WebElement element = driver.findElement(By.id(""));
		// clear in editable field (textbox, textarea, dropdown)
		element.clear();

		// input value in editable field (textbox, textarea, dropdown)
		element.sendKeys(Keys.ENTER);//**

		// click in button/link/radio link/ checkbox
		element.click(); //**

		// get attribute of element (invisible verify text in textbox ) // email or
		// phone
		element.getAttribute("Attribute"); //**

		// get css value of element: font/size/color..(verify color of the button.. rgba
		// > hexa)
		element.getCssValue("attribute of CSS");

		// GUI
		element.getLocation();

		element.getRect();

		element.getSize();

		// take screenshot -> attach to HTML report
		
		element.getScreenshotAs(OutputType.FILE); //**
		element.getScreenshotAs(OutputType.BASE64);
		element.getScreenshotAs(OutputType.BYTES);
		
		// Get tagname
		// By.id/class/css/name 
		element.getTagName();
		
		// get text of header/ link/ lable/ error/ success message
		element.getText(); // # attribute //**
		
		//Verify test case, (nhìn thấy vào thao tác được)
		element.isDisplayed();//**
		Assert.assertTrue(element.isDisplayed());
		
		
		//co thao tac duoc hay k (k bi disable/ read-only field)
		element.isEnabled();//**
		
		// check element xem duoc chon hay chua (radio/checkbox/dropdown)
		element.isSelected(); //**
		
		
		
		
	}

	@Test
	public void TC_02_() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
