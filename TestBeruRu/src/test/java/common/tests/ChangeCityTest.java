package common.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.qameta.allure.Step;
import pageobjects.HomePage;
import pageobjects.SignInPage;

import junit.framework.Assert;

public class ChangeCityTest extends BaseTest {
	
	@Test//(dependsOnGroups = {"LoginTest"})
	@Parameters({"newCityParam", "loginParam", "passwordParam"})
	public void changeCity(String newCity, String login, String password) {		
		HomePage homePage = new HomePage(driver, wait);
		homePage.goToUrl();
		homePage.cancelWindow();
		// Вызов метода изменения города
		homePage.changeCity(newCity);
		
		// Проверка, что название города изменилось
		String currentCity = homePage.getCity();
		cityHasChanged(currentCity, newCity);
		
		// Авторизация на сайте
		SignInPage signInPage = homePage.clickSignInBtn();
		signInPage.signIn(login, password);	
		
		// Сравнение значения города в верхнем углу и города доставки
		String deliveryCity = homePage.goToSettings().getDeliveryCity();	
		compareCityNames(deliveryCity, currentCity);
	}
	
	@Step("Проверка, что значение города изменилось")
	public void cityHasChanged(String currentCity, String newCity) {
		Assert.assertEquals("City name hasn't changed", currentCity.trim(), newCity.trim());
		ScreenshotMaker.makeScreenshot(driver);
	}
	
	@Step("Проверка, что после авторизации значение города в верхнем углу и города доставки совпадают")
	public void compareCityNames(String deliveryCity, String currentCity) {
		Assert.assertEquals("Delivery city and current city doesn't match", 
							deliveryCity.trim(), currentCity.trim());
		ScreenshotMaker.makeScreenshot(driver);
	}
}
