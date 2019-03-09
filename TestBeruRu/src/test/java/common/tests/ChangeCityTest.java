package common.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.SignInPage;

import junit.framework.Assert;

public class ChangeCityTest extends BaseTest {
	@Parameters({"newCityParam", "loginParam", "passwordParam"})
	@Test(priority = 1)
	public void okNewCity(String newCity, String login, String password) {
		
		System.out.println("Second test");	
		System.out.println("New city is " + newCity);	
		HomePage homePage = new HomePage(driver, wait);
		homePage.goToUrl();
		homePage.cancelWindow();	
		homePage.changeCity(newCity);
		
		try { 
			Thread.sleep(2000); 
		} catch(Exception e) { 
			System.out.println("Error");
		}
		
		String currentCity = homePage.getCity();
		Assert.assertTrue("City name hasn't changed", currentCity.contains(newCity));
			  
		SignInPage signInPage = homePage.clickSignInBtn();
		signInPage.signIn(login, password);
		
		String deliveryCity = homePage.goToSettings().getDeliveryCity();	
		Assert.assertTrue("Delivery city and current city doesn't match", 
				          deliveryCity.contains(currentCity));	
	}
}
