package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Attachment;

public class ScreenshotMaker {
	
	@Attachment(value = "Page screenshot", type = "image/png")
	public static byte[] makeScreenshot(WebDriver driver) {
		if (driver == null) {
			return null;
		}
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
	
}
