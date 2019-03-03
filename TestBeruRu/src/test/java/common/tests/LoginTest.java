package common.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.SignInPage;

public class LoginTest extends BaseTest {
	String validLogin = "testBeruRu@yandex.ru";
	String validPassword = "kulikandlake";
	
	@Test
	public void validSignIn() {
		System.out.println("Valid sign in test...");	
		HomePage homePage = new HomePage(driver, wait);
		homePage.goToUrl();
		homePage.cancelWindow();
		
		SignInPage signInPage = homePage.clickSignInBtn();
		signInPage.signIn(validLogin, validPassword);
		
		Assert.assertTrue(homePage.phraseChange());			
	    Assert.assertEquals(homePage.getLogin(), validLogin);
	}
	
}
