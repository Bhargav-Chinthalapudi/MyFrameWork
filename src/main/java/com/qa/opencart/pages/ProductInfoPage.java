package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By quantity = By.id("input-quantity");
	private By addToCart = By.id("button-cart");
	private By successmsg = By.cssSelector("div.alert.alert-success");
	private By viewcart = By.cssSelector("div#cart button");
	private By checkout = By.xpath("(//p[@class='text-right']/a)[2]");
	private By prodmetadata = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By prodpricedata = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeaderText() {
		return eleUtil.doGetText(productHeader);
	}

	public int getProductImageCount() {
		return eleUtil.waitForElementsVisible(productImages, Constants.DEFAULT_TIME_OUT).size();
	}

	public String addQuantity(String quantitynum) {

		eleUtil.getElement(quantity).clear();
		eleUtil.doSendKeys(quantity, quantitynum);
		eleUtil.doClick(addToCart);
		eleUtil.waitForElementPresenceWithWebDriverWait(successmsg, 10, 500);
		return eleUtil.doGetText(successmsg).substring(0, 58);

	}

	public Cart viewCart() {
		eleUtil.doClick(viewcart);
		eleUtil.doClick(checkout);
		return new Cart(driver);

	}

	public Map<String,String> prodMetaData() {
		Map<String, String> prodMap = new HashMap<String, String>();
		String headername = eleUtil.doGetText(productHeader);
		prodMap.put("ProductName", headername);
		getProdMetaData(prodMap);
		getProdPriceData(prodMap);
		return prodMap;

	}

	private void getProdMetaData(Map<String, String> prodMap) {
		List<WebElement> metaData = eleUtil.getElements(prodmetadata);
		for (WebElement e : metaData) {
			String metaText = e.getText();
			String metaKey = metaText.split(":")[0].trim();
			String metaValue = metaText.split(":")[1].trim();
			prodMap.put(metaKey, metaValue);
		}
	}

	private void getProdPriceData(Map<String, String> prodMap) {
		List<WebElement> metaPrice = eleUtil.getElements(prodpricedata);
		String actPrice = metaPrice.get(0).getText().trim();
		String exTaxPrice = metaPrice.get(1).getText().trim();
		prodMap.put("price", actPrice);
		prodMap.put("ExTaxPrice", exTaxPrice.split(":")[1].trim());
	}

}
