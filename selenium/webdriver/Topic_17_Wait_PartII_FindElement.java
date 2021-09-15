package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_PartII_FindElement {
	WebDriver driver;
	String Path = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", Path + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
	}
	
	public void TC_01_Find_Element() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://facebook.com/");
	
		// Tim thay 1 Element
		// Tim thay Element se dung lai luon
		// Sau moi 0.5s thi tim lai 1 lan cho den khi tim ra Element
		driver.findElement(By.cssSelector("#email"));
		
		
		
		// Khong tim thay Element nao
		// Chay het 15s tim lai sau moi 0.5s 
		// Khong tim thay element nao trong 15s 
		// tra ve 1 exception NoSuchElementException
		driver.findElement(By.cssSelector("#tiki"));

		
		// Tim thay nhieu Element
		// Tuong tac voi Element dau tien
		driver.findElement(By.cssSelector("#pageFooter a"));
		
	}

	@Test
	public void TC_02_Find_Elements() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://facebook.com/");

		List<WebElement> elements;
		// Tim thay 1 Element
		// Tra ve chuoi 1 phan tu Element
		elements = driver.findElements(By.cssSelector("#email"));
		
		// Khong tim thay Element nao
		// tra ve chuoi khong phan tu Element
		// chay het timeout
		elements = driver.findElements(By.cssSelector("#tiki"));
		
		// Tim thay nhieu Element
		// Tra ve List n phan tu Element 
		elements = driver.findElements(By.cssSelector("#pageFooter a"));
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
