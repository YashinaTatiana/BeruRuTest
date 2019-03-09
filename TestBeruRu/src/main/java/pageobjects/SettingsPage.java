package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SettingsPage extends BasePage {
	
	//	Локатор для получения названия города на странице "Настройки"
    private By cityBy = By.className("link__inner");
	
	public SettingsPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	// Метод, возвращающий город доставки
	public String getDeliveryCity() {
		WebElement elementCity = driver.findElement(cityBy);
		this.scrollTo(elementCity);
		
		System.out.println("City: " + elementCity.getText());
		return elementCity.getText();
	}
}
