package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.qameta.allure.Step;
import pageobjects.PurchasePage;
import utils.Parameters;
import utils.ScreenshotMaker;

public class SearchPage extends BasePage {
	
	// Локатор для поля ввода нижней границы цены
	private By priceFromBy = By.cssSelector("input#glpricefrom");
	// Локатор для поля ввода верхней границы цены
	private By priceToBy = By.cssSelector("input#glpriceto");	
	// Div товаров
	private By productsBy = By.cssSelector("div.n-snippet-list div.grid-snippet");	
	// Цена товара
	private By priceBy = By.cssSelector("span._1u3j_pk1db span"); 
	// Кнопка добавления товара в корзину
	private By buyBy = By.cssSelector("button._4qhIn2-ESi._3OWdR9kZRH.THqSbzx07u");	
	// Ссылка на переход к корзине
	private By goToBasketBy = By.xpath("//span[text()='Перейти в корзину']");
	// Кнопка "Показать еще"
	private By byShowMoreBtn = By.cssSelector("div.n-pager-more__button.pager-loader_preload a");
	// Спиннер 
	private By bySpinner = By.cssSelector("div.spin.spin_js_inited.spin_progress_yes");
	// Сообщение 'Найдено n щеток'
	private By byFind = By.className("_1PQIIOelRL");
	SearchPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	public void setPriceFrom(String priceFrom) {
		enterText(priceFromBy, priceFrom);
	}
	
	public void setPriceTo(String priceTo) {
		enterText(priceToBy, priceTo);
	}
	
	public double getPriceFrom() {
		return Double.parseDouble(this.getValue(priceFromBy));
	}
	
	public double getPriceTo() {
		return Double.parseDouble(this.getValue(priceToBy));
	}
	
	public List<WebElement> getProductList() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(productsBy));
		return driver.findElements(productsBy);
	}
		
	
	public boolean checkPrice() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(byFind));
		// click on button Show more
		wait.until(ExpectedConditions.visibilityOfElementLocated(byShowMoreBtn));
		wait.until(ExpectedConditions.elementToBeClickable(byShowMoreBtn));
		click(byShowMoreBtn);	
		// wait spinner
		wait.until(ExpectedConditions.visibilityOfElementLocated(bySpinner));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(bySpinner));		 
		// check prices
		double from = getPriceFrom();
		double to = getPriceTo();	
		List<WebElement> productList = getProductList();		
		for (WebElement element : productList) {
			double cost = Double.parseDouble(element.findElement(priceBy).getText().replaceAll("\\s",""));
			System.out.println(cost);
			if ((cost > to) || (cost < from)) {
				return false;
			}
		}
		return true;
	}
	
	public Boolean buyProduct() {	
		List<WebElement> productList = this.getProductList();
		if (productList.size() <= 1) {
			System.out.println(productList.size());
			return false;
		}
		int i = productList.size() - 2;				
		WebElement buyBtn = productList.get(i).findElement(buyBy);
		scrollTo(buyBtn);
		click(buyBtn);	
		click(goToBasketBy);
		return true;
	}	
	
	@Step("Input price limits")
	public void inputPriceLimits() {
		setPriceFrom(Parameters.PRICE_FROM);
		setPriceTo(Parameters.PRICE_TO);
	}
	
	// Проверка того, что отображаются щетки с ценами в заданном диапазоне
	@Step("Check that brushes are displayed with prices in the specified range")
	public void checkPricesInRange() {
		Assert.assertTrue(checkPrice(), "Price doesn't match");
	}
	
	// Проверка того, что список содержит по крайней мере 2 элемента,
	// т.е. существует возможность купить предпоследнюю щетку
	@Step("Check that the list contains at least 2 elements and it possible to buy the last but one")
	public void checkIfCanBuy() {
		Assert.assertTrue(buyProduct(), "List length is not correct");
	}
	
}
