package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ResultsPage {

	private WebDriver driver;
	private ElementUtil eleutil;
	private By searchHeader = By.cssSelector("div#logo h1");

	private By productresults = By.cssSelector("div.caption a");

	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	public String getSearchHeaderName() {
		return eleutil.doGetText(searchHeader);
	}

	public int getSearchProducListCount() {
		return eleutil.waitForElementsVisible(productresults, Constants.DEFAULT_TIME_OUT).size();
	}
	
	public ProductInfoPage selectProduct(String mainProductName) {
		
		List<WebElement> searchList=eleutil.waitForElementsVisible(productresults, Constants.DEFAULT_TIME_OUT);
		for(WebElement e : searchList) {
			String text=e.getText().trim();
			if(text.equalsIgnoreCase(mainProductName)){
				e.click();
				break;
			}
		}
		
		return new ProductInfoPage(driver);
	}

}
