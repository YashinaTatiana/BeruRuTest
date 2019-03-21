package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.BasePage;

public class SignInPage extends BasePage {
	
	// Поле ввода логина
	private By userLogin = By.id("passp-field-login");
	// Поле ввода пароля
	private By userPassword = By.id("passp-field-passwd");
	// Кнопка подтверждения 
	private By submitBtn = By.className("button2_type_submit");
		
	public SignInPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	// Метод авторизации
	public void signIn(String login, String password) {	
		enterText(userLogin, login);
		click(submitBtn); 
		enterText(userPassword, password);
		click(submitBtn); 
	}

}
