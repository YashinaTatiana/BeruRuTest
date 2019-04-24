package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	public WebDriver driver;
	public WebDriverWait wait;
	protected String url;
	
	BasePage(WebDriver driver, WebDriverWait wait){
		this.driver = driver;
		this.wait = wait;
	}
	
	public void click(By elementBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
		driver.findElement(elementBy).click();
	}
	
	public void click(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	public void enterText(By elementBy, String str) {	
		wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
		WebElement elem = driver.findElement(elementBy);
		elem.clear();
		elem.sendKeys(str);
	}
	
	public String read(By elementBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
		return read(driver.findElement(elementBy));
	}
	
	public String read(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getText();
	}
	
	public String getValue(By elementBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
		return driver.findElement(elementBy).getAttribute("value");
	}
	
	public void scrollTo(WebElement element) {
		((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView();", element);
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public void goToUrl() {
		driver.navigate().to(url);
	}

	public boolean verifyPageTitle(String pageTitle) {
		return getPageTitle().contains(pageTitle);
	}
}
