package common.tests;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pageobjects.HomePage;
import pageobjects.PurchasePage;
import pageobjects.SearchPage;
import utils.Parameters;

public class BuyTest extends BaseTest {

	@Test(description="Check buy product functionality")
	@Description("Check buy product functionality")
	public void buyElem() {
		HomePage homePage = new HomePage(driver, wait);
		homePage.goToUrl();
		homePage.cancelWindow();	
		SearchPage searchPage = homePage.search(Parameters.PRODUCT);
		searchPage.inputPriceLimits();
		searchPage.checkPricesInRange();
		searchPage.checkIfCanBuy();
		PurchasePage purchasePage = new PurchasePage(driver, wait);		
		purchasePage.checkToFreeDelivery();
		purchasePage.getPrices();
		purchasePage.checkPrice(purchasePage.productPrice, 
				purchasePage.deliveryPrice, purchasePage.discount);			
		// Увеличение количества щеток
		purchasePage.addProducts(Parameters.PRICE_TO);		
		purchasePage.checkIsFreeDelivery();
		purchasePage.getPrices();
		purchasePage.checkPrice(purchasePage.productPrice, 
				purchasePage.deliveryPrice, purchasePage.discount);	
	}

}
