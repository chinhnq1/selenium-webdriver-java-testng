package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Command_I {
	WebDriver driver;
	String Path = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", Path + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Browser() {
		// Open an url
		driver.get("https://www.messenger.com/");		//*
		
		//close a tab active
		driver.close();
		
		// close all tab
		driver.quit();		//*
		
		// take ID of active tab 
		String messengerID = driver.getWindowHandle();
		
		// take ID of all tab 
		driver.getWindowHandles();
		
		// switch to a tab in the web
		driver.switchTo().window(messengerID);		//*
		
		// find a web element depend on a locator
		WebElement emailTextbox= driver.findElement(By.id(""));		//*
		
		// find all element depend on a locator
		List<WebElement> textboxes = driver.findElements(By.id(""));	//*
		
		// return Ulr of current page
		String homePageUrl = driver.getCurrentUrl();	//*
		
		//return HTML source
		String homePageSource = driver.getPageSource();
		
		// return Page title
		String homePageTitle = driver.getTitle();
		
		//Get or delete cookie of page
		driver.manage().deleteAllCookies();
		
		// Build framework: Get log of browser
		driver.manage().logs().getAvailableLogTypes();
		
		// Wait for finding an element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);	//*
		
		//Wait for loading one page successfully (Option)
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		
		// Wait for execute one script successfuly
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		// Open browser full screen
		driver.manage().window().fullscreen();
		
		// Open browser maximine
		driver.manage().window().maximize();	//*
		
		// get position of window
		driver.manage().window().getPosition();
		
		// set position of window
		driver.manage().window().setPosition(new Point(0, 0) );
		
		// get the size of window
		driver.manage().window().getSize();
		driver.manage().window().setSize(new Dimension(1920, 1080));
		
		// back to page
		driver.navigate().back();
		
		// foward to page
		driver.navigate().forward();
		
		//refresh page 
		driver.navigate().refresh();
		
		// Keep history 
		driver.navigate().to("https://www.messenger.com/");
		
		// Window/tab 
		//Alert
		//Frame/Iframe
		driver.switchTo().alert();	//*
		driver.switchTo().window("");	//*
		driver.switchTo().frame("");	//*
	}

	@Test
	public void TC_02_() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
