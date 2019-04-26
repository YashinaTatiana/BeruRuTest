package utils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import common.tests.BaseTest;
import utils.ScreenshotMaker;

public class CustomTestListener extends TestListenerAdapter {
	
	    @Override
	    public void onTestFailure(ITestResult result) {
	    	WebDriver driver = ((BaseTest) result.getInstance()).getDriver();
	        ScreenshotMaker.makeScreenshot(driver);
	        if (result.getThrowable()!=null) {
	            result.getThrowable().printStackTrace();
	        }
	    }

}
