package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_DropDown {
	WebDriver driver;
	String Path = System.getProperty("user.dir");

	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", Path + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
	}

	//@Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		By parentBy = By.id("number-button");
		By childBy = By.cssSelector("ul#number-menu li");

		selectItemDropDown(parentBy, childBy, "5");

		Assert.assertTrue(isElementDisplayed(
				By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']")));

		selectItemDropDown(parentBy, childBy, "7");
		Assert.assertTrue(isElementDisplayed(
				By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='7']")));

		selectItemDropDown(parentBy, childBy, "19");
		Assert.assertTrue(isElementDisplayed(
				By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='19']")));

		selectItemDropDown(parentBy, childBy, "15");
		Assert.assertTrue(isElementDisplayed(
				By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='15']")));
	}

	//@Test
	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		By parentBy = By.xpath("//i[@class ='dropdown icon']");
		By childBy = By.xpath("//div[@role='option']/span");

		selectItemDropDown(parentBy, childBy, "Jenny Hess");

		Assert.assertTrue(isElementDisplayed(
				By.xpath("//div[@role='alert' and text() = 'Jenny Hess']")));

		selectItemDropDown(parentBy, childBy, "Elliot Fu");
		Assert.assertTrue(isElementDisplayed(
				By.xpath("//div[@role='alert' and text() = 'Elliot Fu']")));

		selectItemDropDown(parentBy, childBy, "Matt");
		Assert.assertTrue(isElementDisplayed(
				By.xpath("//div[@role='alert' and text() = 'Matt']")));

	}

	@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		By parentBy = By.xpath("//li[@class ='dropdown-toggle']");
		By childBy = By.cssSelector("ul.dropdown-menu a");

		selectItemDropDown(parentBy, childBy, "Second Option");

		Assert.assertTrue(isElementDisplayed(
				By.xpath("//li[@class ='dropdown-toggle' and contains(text(),'Second Option')]")));
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	public void selectItemDropDown(By parentBy, By childBy, String expectedTextItem) {
		driver.findElement(parentBy).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));

		List<WebElement> allItems = driver.findElements(childBy);

		for (WebElement item : allItems) {
			if (item.getText().equals(expectedTextItem)) {
				if (item.isDisplayed()) {
					item.click();
				} else {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					item.click();
				}
			}
		}
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
}
