package pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.qameta.allure.Step;
import utils.ScreenshotMaker;


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
	// Стоимость товара с учетом скидки
	private Double curPrice;
	// Загрузка
	private By byLoader = By.className("A2ZAPkIo1a");

	public static double productPrice;
	public static double deliveryPrice;
	public static double discount;
	
	public PurchasePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	// Метод получения цены 
	public double getPrice(By by) {
		return getPrice(driver.findElement(by));
	}
	
	public double getPrice(WebElement elem) {
		String priceStr = read(elem).replaceAll("[^0-9]", "");
		if (priceStr.isEmpty()) {
			return 0;
		} else {
			return Double.parseDouble(priceStr);
		}
	}

	
	// Метод, проверяющий, что итоговая цена равна <стоимость щетки> + <доставка>
	@Step("Check prices")
	public void checkPrice(double productPrice, double deliveryPrice, double discount) {
		curPrice = productPrice - discount; 		
		double wholeSum = getPrice(priceBy);
		Assert.assertTrue( wholeSum ==  (deliveryPrice + curPrice), "Price doesn't correct");
	}
	
	// Метод добавления щеток, необходимых до бесплатной доставки
	public void addProducts(String priceTo) {
		double product =  getPrice(productCostBy);
		double maxPrice = Double.parseDouble(priceTo);		
		WebElement plusBtn = driver.findElement(addMoreBy);
		
		while (curPrice <= maxPrice) {
			wait.until(ExpectedConditions.elementToBeClickable(addMoreBy));
			plusBtn.click();	
			curPrice += product;
		}			
		wait.until(ExpectedConditions.visibilityOfElementLocated(byLoader));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(byLoader));
	}	
	
	// Метод, возвращаюший значение до бесплатной доставки
	public double isFreeDelivery() {
		if (this.read(deliveryCostBy).contains("бесплатно")) {
			return 0;
		} else {
			return getPrice(freeDeliveryBy);
		}			
	}
	
	// Проверка значения "До бесплатной доставки осталось"
	@Step("Checking the value \"Until free delivery left \"")
	public void checkToFreeDelivery() {
		Assert.assertTrue(0.0 != isFreeDelivery(), "Delivery is already free");
	}
		
	@Step("Verifying Free Delivery Received")
	public void checkIsFreeDelivery() {
		Assert.assertEquals(0.0, isFreeDelivery(), "Delivery doesn't free");	
	}
	
	// Проверка того, что итоговая цена равна <стоимость щетки> + <доставка>	
	@Step("Get prices")
	public void getPrices() {
		productPrice = getPrice(productCostBy);
		deliveryPrice = getPrice(deliveryCostBy);
		discount = 0;			
		try {
			WebElement discountExist = driver.findElement(discountBy); 
			discount = getPrice(discountExist); 
		} catch(Exception ex) {System.out.println("No discount");};
	}	
	
}
