package test;

import org.openqa.selenium.NoSuchElementException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import base.BaseLib;
import base.BrowserFactory;
import extentReporting.ExtentManager;
import extentReporting.TestListener;
import pages.AmazonHomePage;
import pages.TraversePages;


@Listeners(extentReporting.TestListener.class)		
public class Tests {

	@BeforeSuite
	public void setUp() {
		BaseLib.preConditon();
		ExtentManager.createInstance();
	}

	AmazonHomePage amp;
	TraversePages tp ;

	@Test
	public void searcht() throws InterruptedException {
		amp	= new AmazonHomePage(BrowserFactory.instance);
		tp = new TraversePages(BrowserFactory.instance);
		amp.search();
		Thread.sleep(2000);
		tp.traversal();
		tp.addToCart();
	}

	@Test(dependsOnMethods={"searcht"})
	public void priceAssertion() {
		try {
			tp.assertPrice();
			TestListener.test.get().log(Status.INFO,"Price is same on Listing page and Cart Page");}
		catch(NoSuchElementException ne) {
			TestListener.test.get().log(Status.INFO,"Element not found");
		}

	}

	@Test(dependsOnMethods={"searcht"})
	public void assertCart() {
		try {
			tp.assertItems();
			TestListener.test.get().log(Status.INFO,"No of items  is/are  same on Listing page and Cart Page");}
		catch(NoSuchElementException e) {
			TestListener.test.get().log(Status.INFO,"Element not found");
		}
	}

	@AfterSuite
	public void flush() {
		BrowserFactory.instance.quit();
		TestListener.extent.flush();
	}
}
