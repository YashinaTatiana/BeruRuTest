package common.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pageobjects.HomePage;

public class ChangeCityTest extends BaseTest {
	
	@DataProvider(name = "getNewCity")
	public Object[][] getData(){
		return new Object[][] {
			{"Хвалынск"}, 
			{"Самара"}, 
			{"Энгельс"} };
	}
	
	@Description("Check Change city functionality")
	@Test(description="Check Change city functionality", dependsOnGroups = {"LoginTest"}, 
		dataProvider = "getNewCity")
	public void changeCity(String newCity) {		
		HomePage homePage = new HomePage(driver, wait);
		homePage.goToUrl();
		homePage.cancelWindow();
		// Вызов метода изменения города
		homePage.changeCity(newCity);		
		// Проверка, что название города изменилось
		homePage.checkCityHasChanged(newCity);
		// Авторизация на сайте
		homePage.signIn();
		homePage.compareCityNames();
	}
	
}
