package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_JavaScript_Executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String Path = System.getProperty("user.dir");
	String firstName, lastName, email, password, confirmPassword;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", Path + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;
	}

	// @Test
	public void TC_01_Live_Guru() {
		driver.get("http://live.demoguru99.com/");
		sleepInSecond(3);
		String domainHomePage = (String) executeForBrowser("return document.domain"); // need to return
		Assert.assertEquals(domainHomePage, "live.demoguru99.com");

		String urlHomePage = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(urlHomePage, "http://live.demoguru99.com/");

		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");

		hightlightElement("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//button");
		clickToElementByJS(
				"//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//button");
		sleepInSecond(2);
		String samsungText = getInnerText();

		Assert.assertTrue(samsungText.contains("Samsung Galaxy was added to your shopping cart."));

		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");

		scrollToBottomPage();
		sleepInSecond(2);

		sendkeyToElementByJS("//input[@id='newsletter']", randomEmail());

		sleepInSecond(2);
		hightlightElement("//button[@title='Subscribe']");
		clickToElementByJS("//button[@title='Subscribe']");

		sleepInSecond(5);
		String subcriptionText = getInnerText();
		sleepInSecond(2);
		Assert.assertTrue(subcriptionText.contains("Thank you for your subscription."));

		driver.get("http://demo.guru99.com/v4/");

		String demoGuruDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(demoGuruDomain, "demo.guru99.com");
	}

	// @Test
	public void TC_02_sieuThi_Get_Validation_Message() {
		driver.get("https://sieuthimaymocthietbi.com/account/register");
		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();

		String messageValidaton = getElementValidationMessage("//input[@id='lastName']");
		Assert.assertEquals(messageValidaton, "Please fill out this field.");

		driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Automation");
		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();

		messageValidaton = getElementValidationMessage("//input[@id='firstName']");
		Assert.assertEquals(messageValidaton, "Please fill out this field.");

		driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("FC");
		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();

		messageValidaton = getElementValidationMessage("//input[@id='email']");
		Assert.assertEquals(messageValidaton, "Please fill out this field.");

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("fc@");
		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();

		messageValidaton = getElementValidationMessage("//input[@id='email']");
		Assert.assertEquals(messageValidaton, "Please enter an email address.");

		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("fc@33");
		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();

		messageValidaton = getElementValidationMessage("//input[@id='email']");
		Assert.assertEquals(messageValidaton, "Please match the requested format.");

		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("fc@33.com");
		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();

		messageValidaton = getElementValidationMessage("//input[@id='password']");
		Assert.assertEquals(messageValidaton, "Please fill out this field.");

		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("1");
		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();

	}

	@Test
	public void TC_05_Live_DemoGuru_Click() {
		firstName = "Automation" ; 
		lastName = "FC" ;
		email = randomEmail();
		password = "123456" ;
		confirmPassword = password;
		
		driver.get("http://live.demoguru99.com/");
		clickToElementByJS("//div[@id='header-account']//a[text()='My Account']");
		clickToElementByJS("//a[@title='Create an Account']");
		driver.findElement(By.cssSelector("#firstname")).sendKeys(firstName);
		driver.findElement(By.cssSelector("#lastname")).sendKeys(lastName);
		driver.findElement(By.cssSelector("#email_address")).sendKeys(email);
		driver.findElement(By.cssSelector("#password")).sendKeys(password);
		driver.findElement(By.cssSelector("#confirmation")).sendKeys(confirmPassword);
		
		clickToElementByJS("//span[text()='Register']");
		sleepInSecond(5);
		String verifyMessage = getInnerText();
		Assert.assertTrue(verifyMessage.contains("Thank you for registering with Main Website Store."));
		
		clickToElementByJS("//a[text()='Log Out']");
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.page-title>h2")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean isExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElement(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	public void sleepInSecond(long seconds) {

		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ie) {
		}
	}

	public String randomEmail() {
		Random rand = new Random();
		String email = "Automation" + rand.nextInt(99999) + "@gmail.com";
		return email;
	}
}
