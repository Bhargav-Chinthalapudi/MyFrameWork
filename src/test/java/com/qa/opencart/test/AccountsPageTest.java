package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void acountPageSetup() {
		accPage = loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Test
	public void accPageTitleTest() {
		String title = accPage.accPageTitle();
		System.out.println("Your account page title is : " + title);
		Assert.assertEquals(title, Constants.ACC_PAGE_TITLE);
	}

	@Test
	public void accPagLogoutLinkExistTest() {

		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

	@Test
	public void accPageSearchLinkExistTest() {

		Assert.assertTrue(accPage.isSearchExist());
	}

	@Test
	public void accPageSectionHeaderTest() {

		List<String> actSecList = accPage.getAccountSecList();
		System.out.println(actSecList);
		Assert.assertEquals(actSecList, Constants.EXP_ACCOUNTS_SECTIONS_LIST);
	}

	@DataProvider
	public Object[][] productData() {
		return new Object[][] { { "macbook" }, { "iMac" }, { "Apple" } };
	}

	@Test(dataProvider = "productData")
	public void searchTest(String productName) {
		resultpage = accPage.doSearch(productName);
		Assert.assertTrue(resultpage.getSearchProducListCount() > 0);

	}

	@DataProvider
	public Object[][] productSelectData() {
		return new Object[][] { { "macbook", "MacBook Pro" }, { "iMac", "iMac" }, { "Apple", "Apple Cinema 30\"" } };
	}

	@Test(dataProvider = "productSelectData")
	public void selectProductTest(String productName, String mainProductName) {
		resultpage = accPage.doSearch(productName);
		resultpage.selectProduct(mainProductName);

	}

}
