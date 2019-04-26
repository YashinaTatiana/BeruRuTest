package common.tests;

import org.testng.annotations.Test;

import io.qameta.allure.Step;
import pageobjects.HomePage;
import pageobjects.PurchasePage;
import pageobjects.SearchPage;
import utils.Parameters;
import utils.ScreenshotMaker;
import junit.framework.Assert;

public class BuyTest extends BaseTest {

	@Test
	public void buyElem() {
		HomePage homePage = new HomePage(driver, wait);
		homePage.goToUrl();
		homePage.cancelWindow();
		
		SearchPage searchPage = homePage.search(Parameters.PRODUCT);
		searchPage.setPriceFrom(Parameters.PRICE_FROM);
		searchPage.setPriceTo(Parameters.PRICE_TO);
		
		checkPricesInRange(searchPage);
		checkIfCanBuy(searchPage);
		PurchasePage purchasePage = new PurchasePage(driver, wait);		
		checkToFreeDelivery(purchasePage);
		checkResultPrice(purchasePage);			
		// Увеличение количества щеток
		purchasePage.addProducts(Parameters.PRICE_TO);		
		checkIsFreeDelivery(purchasePage);
		checkResultPrice(purchasePage);
	}
	
	// Проверка того, что отображаются щетки с ценами в заданном диапазоне
	@Step("Check that brushes are displayed with prices in the specified range")
	public void checkPricesInRange(SearchPage searchPage) {
		Assert.assertTrue("Price doesn't match", searchPage.checkPrice());
		ScreenshotMaker.makeScreenshot(driver);
	}
	
	// Проверка того, что список содержит по крайней мере 2 элемента,
	// т.е. существует возможность купить предпоследнюю щетку
	@Step("Check that the list contains at least 2 elements and it possible to buy the last but one")
	public void checkIfCanBuy(SearchPage searchPage) {
		ScreenshotMaker.makeScreenshot(driver);
		Assert.assertTrue("List length is not correct", searchPage.buyProduct());
	}
	
	// Проверка значения "До бесплатной доставки осталось"
	@Step("Checking the value \"Until free delivery left \"")
	public void checkToFreeDelivery(PurchasePage purchasePage) {
		Assert.assertTrue("Delivery is already free", 0.0 != purchasePage.isFreeDelivery());
		ScreenshotMaker.makeScreenshot(driver);
	}
	
	@Step("Verifying Free Delivery Received")
	public void checkIsFreeDelivery(PurchasePage purchasePage) {
		Assert.assertEquals("Delivery doesn't free", 0.0, purchasePage.isFreeDelivery());	
		ScreenshotMaker.makeScreenshot(driver);
	}
	
	// Проверка того, что итоговая цена равна <стоимость щетки> + <доставка>		
	@Step("Check that the total price is <brush cost> + <shipping>")
	public void checkResultPrice(PurchasePage purchasePage) {
		Assert.assertTrue("Price doesn't correct", purchasePage.checkPrice());
		ScreenshotMaker.makeScreenshot(driver);
	}	
}
