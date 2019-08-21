package base;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

import util.PropertyValues;
import util.WaitStatementLib;

public class BaseLib {
	public static void preConditon()
	{
		String browser = PropertyValues.getPropertyValue("browser");
		System.out.println(browser);
		BrowserFactory.Initialize(browser);
		BrowserFactory.instance.manage().window().maximize();
		BrowserFactory.instance.get(PropertyValues.getPropertyValue("url"));
		Reporter.log("Navigating to the Test URL", true);
		WaitStatementLib.implicitWaitForSecond(BrowserFactory.instance, 10);
	}

	@AfterClass
	public void postCondtion(ITestResult result)
	{
		if(result.isSuccess())
		{
			Reporter.log("Script Passed" , true);
		}
		else
		{
			String fileName = result.getMethod().getMethodName();
			//ScreenshotLib sLib=new  ScreenshotLib();
			//sLib.getScreenshot(fileName);
			Reporter.log("Screenshot has been taken", true);
		}
		BrowserFactory.instance.quit();
		BrowserFactory.instance = null;
		Reporter.log("Closing All Browser...", true);

	}
}
