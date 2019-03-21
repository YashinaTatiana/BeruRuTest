package common.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.qameta.allure.Step;
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
		
		checkLogin(homePage, login);
		checkButtonText(homePage);
	}	
	
	@Step("Проверка, что на главной странице отображается логин")
	public void checkLogin(HomePage homePage, String login) {
		Assert.assertEquals(homePage.getLogin(), login);		
	}
	
	@Step("Проверка, что кнопка “Войти в аккаунт” сменилась на “Мой профиль”")
	public void checkButtonText(HomePage homePage) {
		Assert.assertTrue(homePage.phraseChange());
	}
}
