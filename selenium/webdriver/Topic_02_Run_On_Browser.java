package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_02_Run_On_Browser {
	WebDriver driver;
	String Path = System.getProperty("user.dir");
	
	@Test
	public void TC_02_Firefox_Old_Version() {
		// Firefox 47.0.2
		// Selenium 2.53.1
		// No need Test NG
		// No need Gecko Driver
		driver = new FirefoxDriver();
		driver.get("https://google.com.vn");
		driver.quit();
	}
}
