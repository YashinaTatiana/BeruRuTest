package utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.StepResult;
import pageobjects.BasePage;
import utils.ScreenshotMaker;

public class CustomTestListener extends TestListenerAdapter implements StepLifecycleListener {
	
	    @Override
	    public void onTestFailure(ITestResult result) {
	    	WebDriver driver = BasePage.getDriver();
	        ScreenshotMaker.makeScreenshot(driver);
	        if (result.getThrowable()!=null) {
	            result.getThrowable().printStackTrace();
	        }
	    }
	    	    
	    @Override
	    public void beforeStepStop(StepResult result) {
	    	WebDriver driver = BasePage.getDriver();
	        ScreenshotMaker.makeScreenshot(driver);
	    }

}
