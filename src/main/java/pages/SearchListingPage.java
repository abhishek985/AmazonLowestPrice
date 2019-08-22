package pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import util.PropertyValues;

public class SearchListingPage {
	@FindBy(xpath = "//div[@class='a-row a-size-base a-color-base']/descendant::a/span[1]")
	private List<WebElement> prices;

	@FindBy(xpath = "//div[@class='a-row a-size-base a-color-base']/parent::div/parent::div/parent::div/parent::div/preceding::a[3]")
	private List<WebElement> titles;

	@FindBy(xpath = "//li[@class='a-disabled'][2]")
	private WebElement lastPage;

	public SearchListingPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}


	public void price(Map<Integer,Map<Integer,String>> m ,Integer j) {
		String product = PropertyValues.getPropertyValue("productName");
		int pricefilter = Integer.parseInt(PropertyValues.getPropertyValue("pricegreaterthanfilter"));
		
		int n = Math.min(prices.size(), titles.size());
		for(int i = 0;i< n;i++) {
			if(titles.get(i).getText().toString().contains(product)){
				if(Integer.parseInt(prices.get(i).getText().toString().replace(",", "").replace("₹", "")) > pricefilter){
					
					m.put(Integer.parseInt(prices.get(i).getText().toString().replace(",", "").replace("₹", "")), new HashMap<Integer,String>());

					m.get(Integer.parseInt(prices.get(i).getText().toString().replace(",", "").replace("₹", ""))).put(j, titles.get(i).getAttribute("href"));
				}
			}
		}
	}



	
}

