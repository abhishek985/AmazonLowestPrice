package util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitStatementLib {

	private WaitStatementLib() {
		throw new IllegalStateException("WaitStatementLib class");
	}

	public static void implicitWaitForSecond(WebDriver driver, int time){
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	public static void implicitWaitForMinute(WebDriver driver, int time){
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	public static void explicitWaitForVisibility(WebDriver driver, int time, WebElement element){
		WebDriverWait wait=new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public static void explicitWaitForClickable(WebDriver driver, int time, WebElement element){
		WebDriverWait wait=new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public static void Sleep(int timeInSec){
		try {
			Thread.sleep(timeInSec*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
