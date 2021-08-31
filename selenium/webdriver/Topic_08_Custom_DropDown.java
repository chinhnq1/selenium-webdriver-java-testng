package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
		//System.setProperty("webdriver.chrome.driver", Path + "\\browserDrivers\\chromedriver.exe");
		
		driver = new FirefoxDriver();
		//driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
	}

	// @Test
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

	// @Test
	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		By parentBy = By.xpath("//i[@class ='dropdown icon']");
		By childBy = By.xpath("//div[@role='option']/span");

		selectItemDropDown(parentBy, childBy, "Jenny Hess");

		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role='alert' and text() = 'Jenny Hess']")));

		selectItemDropDown(parentBy, childBy, "Elliot Fu");
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role='alert' and text() = 'Elliot Fu']")));

		selectItemDropDown(parentBy, childBy, "Matt");
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role='alert' and text() = 'Matt']")));

	}

	// @Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		By parentBy = By.xpath("//li[@class ='dropdown-toggle']");
		By childBy = By.cssSelector("ul.dropdown-menu a");

		selectItemDropDown(parentBy, childBy, "Second Option");

		Assert.assertTrue(
				isElementDisplayed(By.xpath("//li[@class ='dropdown-toggle' and contains(text(),'Second Option')]")));
	}

	// @Test
	public void TC_04_Kendo() {
		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");
		// wait for loading demo invisible

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span.kd-loader")));

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span.k-i-loading")));

		selectItemDropDown(By.xpath("//span[@aria-owns='categories_listbox']"),
				By.cssSelector("ul#categories_listbox h3"), "Seafood");

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span.k-i-loading")));

		selectItemDropDown(By.xpath("//span[@aria-owns='products_listbox']"), By.cssSelector("ul#products_listbox li"),
				"Gravad lax");
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span.k-i-loading")));

		selectItemDropDown(By.xpath("//span[@aria-owns='shipTo_listbox']"), By.cssSelector("ul#shipTo_listbox li"),
				"Rua do Mercado, 12");
	}

	//@Test
	public void TC_05_Angular() { 
		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");

		selectItemDropDown(By.cssSelector("span[aria-owns='games_options']"), By.xpath("//ul[@id ='games_options']/li"),
				"Cricket");
		sleep(3);
		
		selectItemDropDown(By.cssSelector("span[aria-owns='games_options']"), By.xpath("//ul[@id ='games_options']/li"),
				"Football");
		sleep(3);
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[@aria-owns='games_options']//input[@role='textbox']")).getAttribute("aria-label"),"Football"); // use only for chrome

	}

	// @Test
	public void TC_06_Editable() {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		selectItemEditableDropDown(By.xpath("//div[@id='default-place']/input"),
				By.xpath("//ul[@class='es-list' and @style]/li"), "Audi");
		sleep(1);
		selectItemEditableDropDown(By.xpath("//div[@id='default-place']/input"),
				By.xpath("//ul[@class='es-list' and @style]/li"), "BMW");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void selectItemDropDown(By parentBy, By childBy, String expectedTextItem) {

		// Cho check xem element duoc phep kich hay chua

		explicitWait.until(ExpectedConditions.elementToBeClickable(parentBy));
		driver.findElement(parentBy).click(); // ham element clickable tra ve 1

		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy)); // ham

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedTextItem)) {
				if (item.isDisplayed()) {
					item.click();
				} else {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					item.click();
				}
				break;
			}
		}
	}

	public void selectItemEditableDropDown(By parentBy, By childBy, String expectedTextItem) {

		// Cho check xem element duoc phep kich hay chua
		driver.findElement(parentBy).clear();

		driver.findElement(parentBy).sendKeys(expectedTextItem);

		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy)); // ham

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedTextItem)) {
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

	public void sleep(long seconds) {

		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ie) {
		}

	}
}
