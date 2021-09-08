package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Mouse_Action {
	WebDriver driver;
	String Path = System.getProperty("user.dir");
	Actions action;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", Path + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		action = new Actions(driver);

	}

	// @Test
	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");

		action.moveToElement(driver.findElement(By.xpath("//label/following-sibling::input"))).perform(); // hay su dung
		Assert.assertTrue(driver.findElement(By.xpath(
				"//div[@class='ui-tooltip-content' and text() = 'We ask for your age only for statistical purposes.']"))
				.isDisplayed());

		// Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(),
		// "We ask for your age only for statistical purposes.");

	}

	// @Test
	public void TC_01_Hover_II() {
		driver.get("http://www.myntra.com/");

		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text() = 'Kids']"))).perform();
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text() = 'Home & Bath']")).click();
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text() ='Kids Home Bath']"))
						.isDisplayed());
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-home-bath");

	}

	// Create list 12 elements
	// Click and hold on first element > hower to last element > nha chuot
	public void TC_02_Click_And_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> numberElement = driver.findElements(By.cssSelector("#selectable>li"));
		action.clickAndHold(numberElement.get(0)).moveToElement(numberElement.get(3)).release().perform();

		Assert.assertEquals(driver.findElements(By.cssSelector("#selectable>li.ui-selected")).size(), 4);

	}

	@Test // press Ctrl > click > key up Ctrl
	public void TC_02_Click_And_Hold_II() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> numberElement = driver.findElements(By.cssSelector("#selectable>li"));
		action.keyDown(Keys.CONTROL).perform();

		action.click(numberElement.get(0)).click(numberElement.get(3)).click(numberElement.get(6)).click(numberElement.get(11)).perform();
		action.keyUp(Keys.CONTROL).perform();

		Assert.assertEquals(driver.findElements(By.cssSelector("#selectable>li.ui-selected")).size(), 4);
	}

	// @Test
	public void TC_01_Right_Click_Action() {
		driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
		driver.manage().window().maximize();
		WebElement button = driver.findElement(By.xpath("//span[text()='right click me']"));

		Actions act = new Actions(driver);

		act.contextClick(button).perform();
		sleepInSecond(1);
		driver.findElement(By.xpath("//ul[@class='context-menu-list context-menu-root']//span[text()='Quit']")).click();

		Alert jsAlert = driver.switchTo().alert();

		Assert.assertEquals(jsAlert.getText(), "clicked: quit");

		jsAlert.accept();
	}

	// @Test
	public void TC_02_Double_Click() {
		driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_ev_ondblclick3");
		driver.manage().window().maximize();

		driver.switchTo().frame("iframeResult");
		WebElement field1 = driver.findElement(By.id("field1"));

		field1.clear();
		field1.sendKeys("AutomationFC");

		Actions action = new Actions(driver);

		WebElement button = driver.findElement(By.xpath("//button[text()='Copy Text']"));

		action.doubleClick(button).perform();

		sleepInSecond(1);

	}

	// @Test
	public void TC_03_Drag_And_Drop() {
		driver.get("http://dhtmlgoodies.com/scripts/drag-drop-custom/demo-drag-drop-3.html");
		driver.manage().window().maximize();
		WebElement madridBox = driver.findElement(By.xpath("(//div[text()='Madrid'])[2]"));
		WebElement ItalyBox = driver.findElement(By.xpath("//div[text()='Italy']"));

		Actions action = new Actions(driver);

		action.dragAndDrop(madridBox, ItalyBox).perform();
		sleepInSecond(1);

	}

	// @Test
	public void TC_04_Drag_And_Drop_Images() {
		driver.get("https://www.globalsqa.com/demo-site/draganddrop/");
		driver.manage().window().maximize();

		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame lazyloaded']")));
		WebElement highTatrasImage = driver.findElement(By.xpath("//h5[text()='High Tatras']"));
		WebElement highTatrasImage2 = driver.findElement(By.xpath("//h5[text()='High Tatras 2']"));

		WebElement trashBox = driver.findElement(By.id("trash"));

		Actions action = new Actions(driver);

		action.dragAndDrop(highTatrasImage, trashBox).perform();

		sleepInSecond(1);

		action.dragAndDrop(highTatrasImage2, trashBox).perform();

		sleepInSecond(1);

	}

	// @Test
	public void TC_05_Hover() {
		driver.get("https://demo.opencart.com/");
		driver.manage().window().maximize();
		WebElement desktopDropDown = driver.findElement(By.xpath("//a[text()='Desktops']"));
		WebElement macItem = driver.findElement(By.xpath("//a[text()='Mac (1)']"));

		Actions action = new Actions(driver);

		action.moveToElement(desktopDropDown).moveToElement(macItem).click().perform();

		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.cssSelector("div[id='content'] h2")).getText(), "Mac");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long seconds) {

		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ie) {
		}
	}
}