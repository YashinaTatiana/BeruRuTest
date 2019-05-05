package common.tests;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pageobjects.HomePage;
import pageobjects.PurchasePage;
import pageobjects.SearchPage;
import utils.Parameters;

public class BuyTest extends BaseTest {

	@Test
	@Description("Check buy product functionality")
	public void buyElem() {
		HomePage homePage = new HomePage(driver, wait);
		homePage.goToUrl();
		homePage.cancelWindow();	
		SearchPage searchPage = homePage.search(Parameters.PRODUCT);
		searchPage.checkPricesInRange();
		searchPage.checkIfCanBuy();
		PurchasePage purchasePage = new PurchasePage(driver, wait);		
		purchasePage.checkToFreeDelivery();
		purchasePage.checkResultPrice();			
		// Увеличение количества щеток
		purchasePage.addProducts(Parameters.PRICE_TO);		
		purchasePage.checkIsFreeDelivery();
		purchasePage.checkResultPrice();
	}

}
