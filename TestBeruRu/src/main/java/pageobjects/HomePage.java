package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.BasePage;
import pageobjects.SignInPage;

public class HomePage extends BasePage {
	
	// Locator for SignIn button
	private By signInBtn = By.className("header2-nav__user");
	
	// Locator for email in My Profile pop-up
	private By loginBy = By.className("header2-user-menu__email");
		
	// Locator for cancel pop-up ads
	private By cancelBy = By.xpath("//div[@class='modal__cell']/div/div");
	
	public HomePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		this.url = "https://beru.ru/";
	}
	
	// Cancel pop-up ads method
	public void cancelWindow() {
		driver.findElement(cancelBy).click();
	}
	
	// Click on SignIn button
	public SignInPage clickSignInBtn() {
	     click(signInBtn);     
		 return new SignInPage(driver, wait);
	}

	// Method that checks the change of the text "Authorization" to "My Profile"
	public boolean phraseChange() {
		String str = this.read(signInBtn);
		return str.contains("Мой профиль");		
	}

	
	public String getLogin() {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(signInBtn)).perform();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loginBy));
		return driver.findElement(loginBy).getText();
	}	
}
