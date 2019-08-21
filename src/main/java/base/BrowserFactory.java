package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	
	public static WebDriver instance = null;
	private BrowserFactory()
	{	
	}
	public static void Initialize(String browser)
	{
		if(instance==null)
			if(browser.equalsIgnoreCase("firefox"))
				{
					WebDriverManager.firefoxdriver().setup();
					instance = new FirefoxDriver();
					System.out.println("Driver launched : FIREFOX");
				}
			else if(browser.equalsIgnoreCase("chrome"))
				{
					WebDriverManager.chromedriver().setup();
					instance = new ChromeDriver();
					System.out.println("Driver launched : CHROME");
				}
		else
			{
				WebDriverManager.firefoxdriver().setup();
				instance = new FirefoxDriver();
				System.out.println("Driver launched : FIREFOX");
			}
	}
}
