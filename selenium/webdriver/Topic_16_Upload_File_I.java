package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Upload_File_I {
	WebDriver driver;
	String Path = System.getProperty("user.dir");
	String dellName = "Dell.png";
	String razerName = "Razer.png";
	String thinkpadName = "Thinkpad.png";
	
	String dellFilePath = Path + File.separator + "UploadFile" + File.separator + dellName;
	String razerFilePath = Path + File.separator + "UploadFile" + File.separator + razerName;
	String thinkpadFilePath = Path + File.separator + "UploadFile" + File.separator + thinkpadName;
	
	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", Path + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
		
//		System.setProperty("webdriver.chrome.driver", Path + "\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();
		
		System.setProperty("webdriver.edge.driver", Path + "\\browserDrivers\\msedgedriver.exe");
		driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Upload_One_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		By pathUpFile = By.xpath("//input[@type='file']");
		
		// Have to declare File Element between Upload file
		driver.findElement(pathUpFile).sendKeys(dellFilePath);
		sleepInSecond(1);
		driver.findElement(pathUpFile).sendKeys(razerFilePath);
		sleepInSecond(1);
		driver.findElement(pathUpFile).sendKeys(thinkpadFilePath);
		sleepInSecond(1);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ dellName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ razerName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ thinkpadName +"']")).isDisplayed());
		
		List<WebElement> startButton = driver.findElements(By.xpath("//button//span[text()='Start']"));
		for (WebElement button : startButton) {
			button.click();
			sleepInSecond(2);
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[text()='"+ dellName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[text()='"+ razerName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[text()='"+ thinkpadName +"']")).isDisplayed());
	}

	@Test
	public void TC_02_Multiple_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		By pathUpFile = By.xpath("//input[@type='file']");
		
		//Upload multiple file: using Enter keyword between FilePath
		driver.findElement(pathUpFile).sendKeys(dellFilePath +"\n"+ razerFilePath +"\n"+ thinkpadFilePath);
		sleepInSecond(1);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ dellName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ razerName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ thinkpadName +"']")).isDisplayed());
		
		List<WebElement> startButton = driver.findElements(By.xpath("//button//span[text()='Start']"));
		for (WebElement button : startButton) {
			button.click();
			sleepInSecond(2);
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[text()='"+ dellName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[text()='"+ razerName +"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[text()='"+ thinkpadName +"']")).isDisplayed());
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
