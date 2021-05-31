package BrowsersGridTests;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FreshWorksTest {

	WebDriver driver;
	@Parameters("browser")
	@BeforeTest
	
	public void setUp(String browser)  {
		if(browser.equalsIgnoreCase("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver();
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("browserName", "chrome");
			try {
				driver = new RemoteWebDriver(new URL("http://54.219.101.60:4444/wd/hub"),cap);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver();
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("browserName", "firefox");
			try {
				driver = new RemoteWebDriver(new URL("http://54.219.101.60:4444/wd/hub"),cap);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.freshworks.com/");
	}
	
	@Test
	public void freshWorkstitleTest() {
		System.out.println(driver.getTitle());
		assertEquals(driver.getTitle(), "A fresh approach to customer engagement");
	}
	
	@Test
	public void getFooterLinksTest() {
		List<WebElement> footerLinksList = driver.findElements(By.cssSelector("ul.footer-nav li a"));
		footerLinksList.forEach(ele -> System.out.println(ele.getText()));
		assertEquals(footerLinksList.size(), 31);		
		
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		
	}
}

























