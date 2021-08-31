package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_Radio_Checkbox {
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

	// @Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		By buttonLogin = By.cssSelector("button.fhs-btn-login");
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

		Assert.assertFalse(driver.findElement(buttonLogin).isEnabled());

		driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("chinh@gmail.com");
		driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("123456");
		Assert.assertTrue(driver.findElement(buttonLogin).isEnabled());

		driver.navigate().refresh();
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled');", driver.findElement(buttonLogin));
		driver.findElement(buttonLogin).click();
		sleepInSecond(1);

		Assert.assertEquals(driver
				.findElement(By.xpath("//div[@class='popup-login-content']"
						+ "//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");

		Assert.assertEquals(
				driver.findElement(By.xpath("//div[@class='popup-login-content']"
						+ "//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(),
				"Thông tin này không thể để trống");
		// Verify color button

		String colorRGBA = driver.findElement(buttonLogin).getCssValue("background-color");

		String colorHexa = Color.fromString(colorRGBA).asHex().toUpperCase();

		Assert.assertEquals(colorHexa, "#C92127");
	}

	// @Test
	public void TC_02_Radio_Default() {
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

		By petrolTwo = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");

		Assert.assertFalse(driver.findElement(petrolTwo).isSelected());

		driver.findElement(petrolTwo).click();

		sleepInSecond(1);

		Assert.assertTrue(driver.findElement(petrolTwo).isSelected());

		By dieselTwo = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input");

		driver.findElement(dieselTwo).click();

		sleepInSecond(1);

		Assert.assertTrue(driver.findElement(dieselTwo).isSelected());

		By petrolThree = By.xpath("//label[text()='3.6 Petrol, 191kW']/preceding-sibling::input");

		Assert.assertFalse(driver.findElement(petrolThree).isEnabled());

	}

	// @Test
	public void TC_03_Checkbox_Default() {

		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

		By rearCheckBox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");

		checkCheckbox(rearCheckBox);

		Assert.assertTrue(driver.findElement(rearCheckBox).isSelected());

		sleepInSecond(1);
		uncheckCheckbox(rearCheckBox);

		Assert.assertFalse(driver.findElement(rearCheckBox).isSelected());
		sleepInSecond(1);

		checkCheckbox(rearCheckBox);

		Assert.assertTrue(driver.findElement(rearCheckBox).isSelected());

		sleepInSecond(1);

	}

	// @Test
	public void TC_04_Radio_Custom() {
		driver.get("https://material.angular.io/components/radio/examples");
		// Click by JS and verify by input
		By winterRadioCheckBox = By.xpath("//input[@value='Winter']");

		clickInElement(winterRadioCheckBox);

		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(winterRadioCheckBox).isSelected());

	}

	//@Test
	public void TC_05_CheckBox_Custom() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		By checkedCheckbox = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
		By indeterminateCheckbox = By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input");

		checkCheckboxJS(checkedCheckbox);
		checkCheckboxJS(indeterminateCheckbox);
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(indeterminateCheckbox).isSelected());

		uncheckCheckboxJS(checkedCheckbox);
		uncheckCheckboxJS(indeterminateCheckbox);
		sleepInSecond(2);

		Assert.assertFalse(driver.findElement(checkedCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(indeterminateCheckbox).isSelected());

	}
	
	//@Test
	public void TC_06_Radio_Checkbox_Googleform() {
		// isSelected chi verify cho the input
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		By canthoCheckBox = By.xpath("//div[@aria-label='Cần Thơ']");
		By haiphongCheckBox = By.xpath("//div[@aria-label='Hải Phòng']");
		
		checkCheckbox(canthoCheckBox);
		
		sleepInSecond(1);

		Assert.assertEquals(driver.findElement(canthoCheckBox).getAttribute("aria-checked"),"true");
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked ='true']")).isDisplayed());
		
		checkCheckbox(haiphongCheckBox);
		
		sleepInSecond(1);
		
		Assert.assertEquals(driver.findElement(canthoCheckBox).getAttribute("aria-checked"),"false");
		
		List<WebElement> checkboxes = driver.findElements(By.xpath("//div[@role='checkbox']"));
		
		for (WebElement checkbox : checkboxes) {
			checkbox.click();
			Assert.assertEquals(checkbox.getAttribute("aria-checked"),"true");
			
		}
			
		
		
	}


	@Test
	public void TC_07_Guru() {
		
		driver.get("http://live.demoguru99.com/index.php/backendlogin");
		driver.findElement(By.id("username")).sendKeys("user01");
		driver.findElement(By.id("login")).sendKeys("guru99com");
		driver.findElement(By.xpath("//input[@title='Login']")).click();
		sleepInSecond(5);
		
		checkCheckboxGuru("Phuong Yen Pham");
		
		checkCheckboxGuru("linh khanh nguyen");
		
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

	public void checkCheckboxGuru (String customerName) {
		WebElement customerBy = driver.findElement(By.xpath("//td[contains(.,'"+customerName+"')]/preceding-sibling::td/input"));
		if (!customerBy.isSelected()) {
			customerBy.click();
			
		}
	}
	public void checkCheckboxJS(By by) {
		if (!driver.findElement(by).isSelected()) {
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
		}
	}

	public void uncheckCheckboxJS(By by) {
		if (driver.findElement(by).isSelected()) {
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
		}
	}

	public void clickInElement(By by) {
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));

	}

	public void checkCheckbox(By by) {
		if (!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}

	public void uncheckCheckbox(By by) {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}

	public void sleepInSecond(long seconds) {

		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ie) {
		}

	}
}
