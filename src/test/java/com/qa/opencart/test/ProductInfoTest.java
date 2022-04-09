package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void productInfoPageSetup() {
		accPage = loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());

	}

	@Test
	public void productHeaderTest() {
		resultpage = accPage.doSearch("macbook");
		prodInfo = resultpage.selectProduct("MacBook Pro");
		String actHeader = prodInfo.getProductHeaderText();
		Assert.assertEquals(actHeader, "MacBook Pro");

	}

	@DataProvider
	public Object[][] getImageData() {
		return new Object[][] { { "macbook", "MacBook Pro", 4 }, { "iMac", "iMac", 3 },
				{ "Apple", "Apple Cinema 30\"", 6 } };
	}

	@Test(dataProvider = "getImageData")
	public void producImageCountTest(String productName, String mainProductName, int imageCount) {
		resultpage = accPage.doSearch(productName);
		prodInfo = resultpage.selectProduct(mainProductName);
		Assert.assertEquals(prodInfo.getProductImageCount(), imageCount);

	}

	@Test
	public void productMetaDataTest() {
		resultpage = accPage.doSearch("macbook");
		prodInfo = resultpage.selectProduct("MacBook Pro");
		Map<String, String> actProdMap = prodInfo.prodMetaData();
		actProdMap.forEach((k, v) -> System.out.println(k + " : " + v));
		softAssert.assertEquals(actProdMap.get("ProductName"), "MacBook Pro");
		softAssert.assertEquals(actProdMap.get("Brand"), "Apple");
		softAssert.assertAll();
	}

	@DataProvider
	public Object[] getProductName() {
		return new Object[][] { { "macbook", "MacBook Pro", "2" } };
	}

	@Test(dataProvider = "getProductName")
	public void addToCartTest(String productname, String mainProductName, String quantity) {
		resultpage = accPage.doSearch(productname);
		prodInfo = resultpage.selectProduct(mainProductName);
		String successmsg = prodInfo.addQuantity(quantity);
		System.out.println(successmsg);
		Assert.assertEquals(successmsg, Constants.SUCCESS_MSG);
		// Assert.assertTrue(successmsg.co);

	}

	@Test
	public void viewCartTest() {
		cart = prodInfo.viewCart();

	}

}
