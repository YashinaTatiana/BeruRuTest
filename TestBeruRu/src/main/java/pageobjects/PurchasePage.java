package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PurchasePage extends BasePage{
	
	// Локатор для добавления еще одного продукта (+)
	private By addMoreBy = By.xpath("//span[text()='+']");		
	// Сумма до бесплатной доставки
	private By freeDeliveryBy = By.cssSelector("span.voCFmXKfcL");
	// Значение поля "Товары"
	private By productCostBy = By.xpath("//span[contains(text(),'Товары')]/following-sibling::span");
	// Значение поля "Доставка с курьером"
	private By deliveryCostBy = By.xpath("//span[contains(text(),'Доставка')]/following-sibling::span");
	//  Значение поля "Скидка на товары"
	private By discountBy = By.xpath("//span[contains(text(),'Скидка')]/folowing-sibling::span");
	// Значение поля "Итого"
	private By priceBy = By.xpath("//span[text()='Итого']/following-sibling::span");	
	
	private Double curPrice;
	
	public PurchasePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	public double getPrice(By by) {
		String priceStr = this.read(by).replaceAll("[^0-9]", "");
		if (priceStr.isEmpty())
			return 0;
		else
			return Double.parseDouble(priceStr);
	}
	
	public Boolean checkPrice() {
		double productPrice = getPrice(productCostBy);
		double deliveryPrice = getPrice(deliveryCostBy);
		double discount = 0;		
		 try { 
			 discount = getPrice(discountBy);
		} catch (Exception e) { };
		 
		curPrice = productPrice - discount; 
		
		double wholeSum = getPrice(priceBy);
		return wholeSum ==  (deliveryPrice + curPrice);
	}
	
	public void addProducts(String priceTo) {
		double product =  getPrice(productCostBy);
		double maxPrice = Double.parseDouble(priceTo);		
		WebElement plusBtn = driver.findElement(addMoreBy);
		
		while (curPrice <= maxPrice) {
			wait.until(ExpectedConditions.elementToBeClickable(addMoreBy));
			plusBtn.click();	
			curPrice += product;
		}			
	}
	
	public Boolean haveFreeDelivery() {
		return this.read(deliveryCostBy).contains("бесплатно");
	}
}
