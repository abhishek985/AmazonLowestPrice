package pages;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.aventstack.extentreports.Status;
import extentReporting.TestListener;
import util.PropertyValues;
import util.WaitStatementLib;
import base.BrowserFactory;
public class TraversePages {

	@FindBy(xpath = "//li[@class='a-disabled'][2]")
	private WebElement lastPage;

	@FindBy(xpath = "//input[@id='add-to-cart-button']")
	private WebElement cartButton;

	@FindBy(xpath = "//h1[@id='title']")
	private WebElement title;

	@FindBy(xpath = "//span[@id=\"priceblock_ourprice\"]")
	private WebElement priceListing;

	@FindBy(xpath = "(//b[contains(text(),'Cart subtotal')][1]/parent::span/following-sibling::span)[1]")
	private WebElement priceCart;

	@FindBy(xpath = "//*[@id=\"hlb-subcart\"]/div[1]/span/span[1]")
	private WebElement cartValue;





	public TraversePages(WebDriver driver) {
		System.out.println("TraversePages initialized");
		TestListener.test.get().log(Status.INFO,"TraversePages initialized");
		PageFactory.initElements(driver, this);
	}

	Map<Integer,Map<Integer,String>> m = new TreeMap<Integer,Map<Integer,String>>();
	String listingPagePrice = "";

	public void traversal() throws InterruptedException
	{
		SearchListingPage slp = new SearchListingPage(BrowserFactory.instance);
		slp.price(m,1);

		int n =Integer.parseInt(lastPage.getText().toString());
		for(Integer i=2;i<=n;i++) {	
			try {
				slp = new SearchListingPage(BrowserFactory.instance);
				String product = PropertyValues.getPropertyValue("productName");
				BrowserFactory.instance.get("https://www.amazon.in/s?k="+product+"&amp;page="+i+"&amp;qid=1566315498&amp;ref=sr_pg_"+i);

				slp.price(m,i);



			}
			catch(NoSuchElementException ne) {

				TestListener.test.get().log(Status.INFO,"No product matching the criteria on page " +i);
				continue;
			}
		}
	}



	public void addToCart() throws InterruptedException {
		TestListener.test.get().log(Status.INFO,String.valueOf(m.size()));

		try {
			int price = m.keySet().iterator().next();
			Map<Integer, String> value =  m.get(m.keySet().iterator().next());

			int page = 0;
			String href = "";

			for(Map.Entry<Integer, String> entry : value.entrySet()) {

				page =entry.getKey();
				href= entry.getValue();
				break;
			}

			BrowserFactory.instance.get(href);
			
			WaitStatementLib.explicitWaitForVisibility(BrowserFactory.instance, 4, cartButton);
			//Thread.sleep(4000);

			TestListener.test.get().log(Status.INFO,String.valueOf(page));

			System.out.println("The product with the lowest price is "+title.getText() +"  and price is "+price + 
					" This product can be found on page "+page);
			TestListener.test.get().log(Status.INFO,"The product with the lowest price is "+title.getText() +"  and price is "+price+". This product can be found on page "+page);
			Thread.sleep(2000);
			listingPagePrice = priceListing.getText().replace(" " , "");
			cartButton.click();
		}
		catch(NoSuchElementException  ne) {
			TestListener.test.get().log(Status.INFO,"No relevant products found" );
		}
	}

	public void assertPrice() {

		String cartPrice = priceCart.getText().replace(" ", "");
		cartPrice =  "₹" + cartPrice ;

		TestListener.test.get().log(Status.INFO,"Listing page price is "+listingPagePrice);
		TestListener.test.get().log(Status.INFO,"Cart page price is "+cartPrice);

		Assert.assertEquals(listingPagePrice, cartPrice);
	}

	public void assertItems() {
		String cart = cartValue.getText();		
		Assert.assertEquals("Cart subtotal (1 item):", cart);
	}

}

