package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.PurchasePage;

public class SearchPage extends BasePage {
	
	// Локатор для поля ввода нижней границы цены
	private By priceFromBy = By.cssSelector("input#glpricefrom");
	// Локатор для поля ввода верхней границы цены
	private By priceToBy = By.cssSelector("input#glpriceto");	
	// Div товаров
	private By productsBy = By.cssSelector("div.n-snippet-list div.grid-snippet");	
	// Цена товара
	private By priceBy = By.cssSelector("span._1u3j_pk1db.n2qB2SKKgz.AJD1X1j5bE span");
	// Сообщение о количестве найденных товаров
	private By findResultBy = By.className("_1PQIIOelRL");
	// Кнопка добавления товара в корзину
	private By buyBy = By.cssSelector("span._2w0qPDYwej");	
	// Ссылка на переход к корзине
	private By goToBasketBy = By.xpath("//span[text()='Перейти в корзину']");
	
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
		waitVisibility(findResultBy);
		return driver.findElements(productsBy);
	}
		
	public boolean checkPrice() {
		double from = getPriceFrom();
		double to = getPriceTo();
		
		List<WebElement> productList = getProductList();
		for (WebElement element : productList) {
			double cost = Double.parseDouble(element.findElement(priceBy).getText().replaceAll("\\s",""));
			if ((cost > to) || (cost < from)) {
				return false;
			}
		}
		return true;
	}
	
	public void buyProduct() {	
		List<WebElement> productList = this.getProductList();
		int i = productList.size() - 2;				
		WebElement buyBtn = productList.get(i).findElement(buyBy);		
	    scrollTo(buyBtn);
		wait.until(ExpectedConditions.elementToBeClickable(buyBtn));
		buyBtn.click();		
		wait.until(ExpectedConditions.elementToBeClickable(goToBasketBy));
		driver.findElement(goToBasketBy).click();
		return;
	}	
}
