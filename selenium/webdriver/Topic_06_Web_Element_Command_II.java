package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Command_II {
	WebDriver driver;
	String Path = System.getProperty("user.dir");

	By emailTextBoxBy = By.id("mail");
	By educationTextArea = By.id("edu");
	By user5Text = By.xpath("//h5[text()='Name: User5']");
	By over18RadioBy = By.id("over_18");
	By developementCheckBox = By.id("development");
	By job01DropdownBy = By.id("job1");
	By job02MultiDropDownBy = By.id("job2");
	By javaCheckBox = By.id("java");
	By slider01By = By.id("slider-1");
	By slider02By = By.id("slider-2");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", Path + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_isDisplay() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement emailTextBox = driver.findElement(By.id("mail"));
		if (emailTextBox.isDisplayed()) {
			emailTextBox.sendKeys("Automation FC");
			System.out.println("Email Textbox is displayed");
		} else {
			System.out.println("Email Textbox is not displayed");

		}
		WebElement over18Radio = driver.findElement(By.id("over_18"));
		if (over18Radio.isDisplayed()) {
			over18Radio.click();
			System.out.println("Over 18 Radio is displayed");
		} else {
			System.out.println("Over 18 Radio is not displayed");

		}
		WebElement educationTextArea = driver.findElement(By.id("edu"));
		if (educationTextArea.isDisplayed()) {
			educationTextArea.sendKeys("Automation Education");
			System.out.println("Education Text Area is displayed");
		} else {
			System.out.println("Education Text Area is not displayed");

		}

		WebElement user5Text = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		if (user5Text.isDisplayed()) {
			System.out.println("User 5 Text is displayed");
		} else {
			System.out.println("User 5 Text is not displayed");

		}
	}

	@Test
	public void TC_02_isDisplay_Refactor() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		By emailTextBoxBy = By.id("mail");
		By over18RadioBy = By.id("over_18");
		By educationTextArea = By.id("edu");
		By user5Text = By.xpath("//h5[text()='Name: User5']");

		if (isElementDisplayed(emailTextBoxBy)) {
			sendKeyToElement(emailTextBoxBy, "Automation Testing");
		}

		if (isElementDisplayed(over18RadioBy)) {
			clickToElement(over18RadioBy);
		}
		if (isElementDisplayed(educationTextArea)) {
			sendKeyToElement(educationTextArea, "Education text area");
		}

		Assert.assertFalse(isElementDisplayed(user5Text));

	}

	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println(by + " is display");
			return true;
		} else {
			System.out.println(by + " is not display");
			return false;

		}
	}

	public boolean isElementSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println(by + " is selected");
			return true;
		} else {
			System.out.println(by + " is not selected");
			return false;

		}
	}

	public boolean isElementEnable(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println(by + " is Enabled");
			return true;
		} else {
			System.out.println(by + " is not enabled");
			return false;

		}
	}

	public void sendKeyToElement(By by, String keys) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(keys);

	}

	public void clickToElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();

	}

	@Test
	public void TC_03_isEnable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Assert.assertTrue(isElementEnable(emailTextBoxBy));
		Assert.assertTrue(isElementEnable(over18RadioBy));
		Assert.assertTrue(isElementEnable(educationTextArea));
		Assert.assertTrue(isElementEnable(job01DropdownBy));

		Assert.assertFalse(isElementEnable(slider02By));

	}

	@Test
	public void TC_04_isSelected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		clickToElement(over18RadioBy);
		clickToElement(developementCheckBox);

		Assert.assertTrue(isElementSelected(over18RadioBy));
		Assert.assertTrue(isElementSelected(developementCheckBox));
		Assert.assertFalse(isElementSelected(javaCheckBox));

		clickToElement(over18RadioBy);
		clickToElement(developementCheckBox);

		Assert.assertTrue(isElementSelected(over18RadioBy));
		Assert.assertFalse(isElementSelected(developementCheckBox));

	}


	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
