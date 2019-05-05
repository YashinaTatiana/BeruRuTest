package common.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import pageobjects.HomePage;
import utils.CustomTestListener;
import utils.Parameters;

@Listeners(CustomTestListener.class)
public class BaseTest {
	protected WebDriver driver;
	protected WebDriverWait wait;
	private static String chromeDriverPath = "D:\\Drivers\\chromedriver\\chromedriver.exe";
	 	
	public WebDriver getDriver() {
	    return driver;
	}
	
    @BeforeMethod
    public void setup () {
    	System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    	driver = new ChromeDriver();
		driver.manage().window().maximize();	
		wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
    }
    
    @AfterMethod
    public void teardown () {
    	if (driver.getCurrentUrl().contains(Parameters.URL)) {
    		HomePage homePage = new HomePage(driver, wait);
    		homePage.logout();
    	}
        driver.quit();
    }
}
