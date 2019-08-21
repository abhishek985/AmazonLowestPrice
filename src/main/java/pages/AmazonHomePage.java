package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import extentReporting.TestListener;
import util.PropertyValues;

public class AmazonHomePage {

	@FindBy(xpath = "//input[@id='twotabsearchtextbox']")
	private WebElement searchBox;

	@FindBy(xpath="//input[@value='Go']")
	private WebElement srchBtn;


	public AmazonHomePage(WebDriver driver) {
		System.out.println("AmazonHomePage initialized");
		TestListener.test.get().log(Status.INFO,"AmazonHomePage initialized");
		PageFactory.initElements(driver, this);
	}

	public void search() {
		TestListener.test.get().log(Status.INFO,"Searching the product");
		
		String product = PropertyValues.getPropertyValue("productName");
		searchBox.sendKeys(product);
		srchBtn.click();
	}

}
