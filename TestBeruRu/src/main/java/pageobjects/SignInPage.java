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
	
	private By errorMessageBy = By.className("passp-form-field__error");
	private String msgError1 = "Введите символы с картинки";
	private String msgError2 = "Неверный пароль";
	
	
	public SignInPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public String getErrorMessage() {
		return read(errorMessageBy);
	}

	public boolean invalidPasswordMsg() {
		String msg = this.read(errorMessageBy);
		return msg.contains(msgError1) || msg.contains(msgError2);
	}
	
	// Authorization method
	public void signIn(String login, String password) {	
		enterText(userLogin, login);
		click(submitBtn); 
		enterText(userPassword, password);
		click(submitBtn); 
	}

}
