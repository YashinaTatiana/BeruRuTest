package common.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Step;
import pageobjects.HomePage;
import pageobjects.SignInPage;
import utils.Parameters;
import utils.ScreenshotMaker;

public class LoginTest extends BaseTest {
	
	@Test(groups = {"LoginTest"})
	public void validSignIn() {
		System.out.println("First test");	
		HomePage homePage = new HomePage(driver, wait);
		homePage.goToUrl();
		homePage.cancelWindow();
		
		signIn(homePage);
		checkLogin(homePage, Parameters.LOGIN);
		checkButtonText(homePage);
		
		homePage.logout();
	}	
	
	@Step("Sign in")
	public void signIn(HomePage homePage) {
		SignInPage signInPage = homePage.clickSignInBtn();
		signInPage.signIn(Parameters.LOGIN, Parameters.PASSWORD);
		ScreenshotMaker.makeScreenshot(driver);
	}
	
	@Step("Verify that login is displayed on the main page")
	public void checkLogin(HomePage homePage, String login) {
		Assert.assertEquals(homePage.getLogin(), login);	
		ScreenshotMaker.makeScreenshot(driver);
	}
	
	@Step("Checking that the \"Login to Account\" button has changed to \"My Profile\"")
	public void checkButtonText(HomePage homePage) {
		Assert.assertTrue(homePage.phraseChange());
		ScreenshotMaker.makeScreenshot(driver);
	}
}
