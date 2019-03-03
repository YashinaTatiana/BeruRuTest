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
	
    public void waitVisibility(By elementBy) {
    	wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
    }
	
	public void click(By elementBy) {
		waitVisibility(elementBy);
		driver.findElement(elementBy).click();
	}
	
	public void enterText(By element, String str) {	
		waitVisibility(element);
		WebElement elem = driver.findElement(element);
		elem.clear();
		elem.sendKeys(str);
	}
	
	public String read(By element) {
		waitVisibility(element);
		return driver.findElement(element).getText();
	}
	
	public String getValue(By element) {
		waitVisibility(element);
		return driver.findElement(element).getAttribute("value");
	}
	
	public void scrollTo(WebElement element) {
		System.out.println("Scrolling to..." + element.getText());
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
