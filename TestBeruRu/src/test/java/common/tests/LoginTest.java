package common.tests;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pageobjects.HomePage;
import utils.Parameters;

public class LoginTest extends BaseTest {
	
	@Description("Verify login functionality")
	@Test(groups = {"LoginTest"})
	public void validSignIn() {
		System.out.println("First test");	
		HomePage homePage = new HomePage(driver, wait);
		homePage.goToUrl();
		homePage.cancelWindow();
		homePage.signIn();
		homePage.checkLogin(Parameters.LOGIN);
		homePage.checkButtonText();
	}	
	
}
