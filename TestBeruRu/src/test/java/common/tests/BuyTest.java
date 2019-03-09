package common.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.PurchasePage;
import pageobjects.SearchPage;

import junit.framework.Assert;

public class BuyTest extends BaseTest {

	@Parameters({"productParam", "priceFromParam", "priceToParam"})
	@Test(priority = 2)
	public void search(String product, String priceFrom, String priceTo) {
		
		HomePage homePage = new HomePage(driver, wait);
		homePage.goToUrl();
		homePage.cancelWindow();
		
		SearchPage searchPage = homePage.search(product);
		searchPage.setPriceFrom(priceFrom);
		searchPage.setPriceTo(priceTo);
		
		Assert.assertTrue("Price doesn't match", searchPage.checkPrice());				
		searchPage.buyProduct();
		
		PurchasePage purchasePage = new PurchasePage(driver, wait);
		Assert.assertTrue("Price doesn't correct", purchasePage.checkPrice());		
		purchasePage.addProducts(priceTo);
		
		try { 
			 Thread.sleep(2000);
		} catch(Exception e) { System.out.println("Error");}
		
		Assert.assertTrue("Price doesn't correct", purchasePage.checkPrice());				
		Assert.assertTrue("Delivery doesn't free", purchasePage.haveFreeDelivery());		
	}
	
}
