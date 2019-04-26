package common.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Step;
import pageobjects.HomePage;
import pageobjects.SignInPage;
import utils.Parameters;
import utils.ScreenshotMaker;
import junit.framework.Assert;

public class ChangeCityTest extends BaseTest {
	
	@DataProvider(name = "getNewCity")
	public Object[][] getData(){
		return new Object[][] {
			{"Хвалынск"}, 
			{"Самара"}, 
			{"Энгельс"} };
	}
	
	@Test(dependsOnGroups = {"LoginTest"}, dataProvider = "getNewCity")
	public void changeCity(String newCity) {		
		HomePage homePage = new HomePage(driver, wait);
		homePage.goToUrl();
		homePage.cancelWindow();
		// Вызов метода изменения города
		homePage.changeCity(newCity);		
		// Проверка, что название города изменилось
		String currentCity = homePage.getCity();
		cityHasChanged(currentCity, newCity);	
		// Авторизация на сайте
		signIn(homePage);
		// Сравнение значения города в верхнем углу и города доставки
		String deliveryCity = homePage.goToSettings().getDeliveryCity();	
		compareCityNames(deliveryCity, currentCity);
		homePage.logout();
	}
	
	@Step("Check that the city name has changed")
	public void cityHasChanged(String currentCity, String newCity) {
		Assert.assertEquals("City name hasn't changed", currentCity.trim(), newCity.trim());
		ScreenshotMaker.makeScreenshot(driver);
	}
	
	@Step("Sign in")
	public void signIn(HomePage homePage) {
		SignInPage signInPage = homePage.clickSignInBtn();
		signInPage.signIn(Parameters.LOGIN, Parameters.PASSWORD);
		ScreenshotMaker.makeScreenshot(driver);
	}
	
	@Step("Check that after authorization the city name in the upper corner and the city of delivery match")
	public void compareCityNames(String deliveryCity, String currentCity) {
		Assert.assertEquals("Delivery city and current city doesn't match", 
							deliveryCity.trim(), currentCity.trim());
		ScreenshotMaker.makeScreenshot(driver);
	}
}
