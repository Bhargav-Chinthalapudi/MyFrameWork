package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {
	private WebDriver driver;
	private ElementUtil elementUtil;
	private By register = By.linkText("Register");
	
	public RegistrationPage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(driver);
	}
	
	public Boolean isRegisterLinkExist() {
		
		return elementUtil.doIsDiplayed(register);
	}

	public String doClickRegisterLink() {
		elementUtil.doClick(register);
		return driver.getTitle();
		
	}

}
