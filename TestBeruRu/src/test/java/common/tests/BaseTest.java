package common.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
	public WebDriver driver;
	public WebDriverWait wait;
	private static String chromeDriverPath = "D:\\Drivers\\chromedriver\\chromedriver.exe";
	 	
	public WebDriver getDriver() {
	    return driver;
	}
	
    @BeforeClass
    public void setup () {
    	System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    	driver = new ChromeDriver();
		driver.manage().window().maximize();	
		wait = new WebDriverWait(driver, 100);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
    }
    
    @AfterClass
    public void teardown () {
        driver.quit();
    }
}
