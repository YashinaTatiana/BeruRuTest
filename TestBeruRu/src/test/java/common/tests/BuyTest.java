package common.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.qameta.allure.Step;
import pageobjects.HomePage;
import pageobjects.PurchasePage;
import pageobjects.SearchPage;

import junit.framework.Assert;

public class BuyTest extends BaseTest {

	@Parameters({"productParam", "priceFromParam", "priceToParam"})
	@Test(priority = 2)
	public void buyElem(String product, String priceFrom, String priceTo) {
		
		HomePage homePage = new HomePage(driver, wait);
		homePage.goToUrl();
		homePage.cancelWindow();
		
		SearchPage searchPage = homePage.search(product);
		searchPage.setPriceFrom(priceFrom);
		searchPage.setPriceTo(priceTo);
		
		checkPricesInRange(searchPage);
		checkIfCanBuy(searchPage);
		PurchasePage purchasePage = new PurchasePage(driver, wait);		
		checkToFreeDelivery(purchasePage);
		checkResultPrice(purchasePage);			
		// Увеличение количества щеток
		purchasePage.addProducts(priceTo);		
		try { 
			 Thread.sleep(2000);
		} catch(Exception e) { System.out.println("Error");}
				
		checkIsFreeDelivery(purchasePage);
		checkResultPrice(purchasePage);
	}
	
	// Проверка того, что отображаются щетки с ценами в заданном диапазоне
	@Step("Проверка, что отображаются щетки с ценами в заданном диапазоне")
	public void checkPricesInRange(SearchPage searchPage) {
		Assert.assertTrue("Price doesn't match", searchPage.checkPrice());		
	}
	
	// Проверка того, что список содержит по крайней мере 2 элемента,
    // т.е. существует возможность купить предпоследнюю щетку
	@Step("Проверка, что список щеток с ценами в заданном диапазоне содержит не менее 2-х элементов")
	public void checkIfCanBuy(SearchPage searchPage) {
		Assert.assertTrue("List length is not correct", searchPage.buyProduct());
	}
	
	// Проверка значения "До бесплатной доставки осталось"
	@Step("Проверка значения \"До бесплатной доставки осталось\"")
	public void checkToFreeDelivery(PurchasePage purchasePage) {
		Assert.assertTrue("Delivery is already free", 0.0 != purchasePage.isFreeDelivery());		
	}
	
	@Step("Проверка, что получена бесплатная доставка")
	public void checkIsFreeDelivery(PurchasePage purchasePage) {
		Assert.assertEquals("Delivery doesn't free", 0.0, purchasePage.isFreeDelivery());		
	}
	
	// Проверка того, что итоговая цена равна <стоимость щетки> + <доставка>		
	@Step("Проверка, что итоговая цена равна <стоимость щетки> + <доставка>")
	public void checkResultPrice(PurchasePage purchasePage) {
		Assert.assertTrue("Price doesn't correct", purchasePage.checkPrice());	
	}	
}
