package common.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.SignInPage;

public class LoginTest extends BaseTest {
	
	@Parameters({"loginParam", "passwordParam"})
	@Test(priority = 0)
	public void validSignIn(String login, String password) {
		System.out.println("First test");	
		HomePage homePage = new HomePage(driver, wait);
		homePage.goToUrl();
		homePage.cancelWindow();
		
		SignInPage signInPage = homePage.clickSignInBtn();
		signInPage.signIn(login, password);
		
		Assert.assertTrue(homePage.phraseChange());
		Assert.assertEquals(homePage.getLogin(), login);
	}
	
}
