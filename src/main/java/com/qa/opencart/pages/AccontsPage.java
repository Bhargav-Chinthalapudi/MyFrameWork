package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccontsPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	private By search = By.name("search");
	private By logout = By.linkText("Logout");
	private By accSecHeaders = By.cssSelector("div#content h2");
	private By searchicon=By.cssSelector("div#search span");

	public AccontsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String accPageTitle() {
		return elementUtil.waitForTitleToBe(Constants.DEFAULT_TIME_OUT, Constants.ACC_PAGE_TITLE);
	}

	public boolean isLogoutLinkExist() {

		return elementUtil.doIsDiplayed(logout);
	}
	
	public boolean isSearchExist() {
		return elementUtil.doIsDiplayed(search);
	}
	
	public List<String> getAccountSecList() {
		List<WebElement> secList=elementUtil.getElements(accSecHeaders);
		List<String> secHeaderList=new ArrayList<String>();
		
		for(WebElement e:secList) {
			secHeaderList.add(e.getText());
		}
		
		return secHeaderList;
	}
	
	public ResultsPage doSearch(String name) {
		System.out.println("The product name : " + name);
		elementUtil.doSendKeys(search, name);
		elementUtil.doClick(searchicon);
		return new ResultsPage(driver);
		
		
	}

}
