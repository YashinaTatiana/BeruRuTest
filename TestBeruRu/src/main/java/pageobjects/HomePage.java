package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.SettingsPage;

import pageobjects.BasePage;
import pageobjects.SignInPage;

public class HomePage extends BasePage {
	
	// Локатор для кнопки "Войти в аккаунт"
	private By signInBtn = By.className("header2-nav__user");	
	// Локатор для e-mail во всплывающем окне "Мой профиль"
	private By loginBy = By.className("header2-user-menu__email");		
	// Локатор для отмены всплывающего окна с рекламой
	private By cancelBy = By.xpath("//div[@class='modal__cell']/div/div");
	
	// Локаторы для второго теста
    // Ссылка на изменение города
	private By cityLinkBy = By.xpath("//span[text()='Регион: ']//span[@class='link__inner']");	
	// Поле ввода нового города
	private By inputCityBy = By.cssSelector("form.region-select-form input");	
	// Список городов при вводе нового города
	private By citiesList = By.className("region-suggest__list-item");
	// Кнопка подтверждения выбора нового города
	private By submitCityBy = By.cssSelector("form.region-select-form button");
	// Категория "Настройки" во всплывающем окне "Мой профиль"
	private By settingsBy = By.className("header2-user-menu__item_type_settings");		
		
	
	public HomePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		this.url = "https://beru.ru/";
	}
	
	// Метод отмены всплывающего окна с рекламой
	public void cancelWindow() {
		driver.findElement(cancelBy).click();
	}
	
	// Метод, осуществляющий нажатие на кнопку "Войти в аккаунт"
	public SignInPage clickSignInBtn() {
	     click(signInBtn);     
		 return new SignInPage(driver, wait);
	}

	// Метод, проверяющий, что кнопка "Войти в аккаунт" изменилась на "Мой профиль" 
	public boolean phraseChange() {
		String str = this.read(signInBtn);
		return str.contains("Мой профиль");		
	}

	// Метод, возвращающий значение логина после авторизации
	public String getLogin() {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(signInBtn)).perform();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginBy));
		return driver.findElement(loginBy).getText();
	}
	
	// Метод, изменяющий город
	public void changeCity(String newCity) {	
		click(cityLinkBy);		
			
		waitVisibility(inputCityBy);
		WebElement inputCity = driver.findElement(inputCityBy);
		inputCity.sendKeys(newCity);
			
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(citiesList));
		inputCity.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		driver.findElement(submitCityBy).submit();
	}
		
	// Метод, возвращающий текущий город
	public String getCity() {
		return driver.findElement(cityLinkBy).getText();
	}
		
	// Переход к странице "Настройки"
	public SettingsPage goToSettings() {
		waitVisibility(signInBtn);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(signInBtn)).perform();	
		click(settingsBy);
		return new SettingsPage(driver, wait);
	}
}
