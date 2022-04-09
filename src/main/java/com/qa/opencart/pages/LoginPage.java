package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	// 1.BY locator or page objectes or object repository

	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@type='submit']");
	private By forgottenpasswordlink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");

	// 2.constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	// 3 page actions/ methods/features
	@Step("getting login page title")
	public String getLoginPageTitle() {
		return driver.getTitle();
	}

	@Step("getting login page url")
	public String getLoginPageUrl() {
		return driver.getCurrentUrl();
	}

	public boolean isForgotPasswordExist() {
		return elementUtil.doIsDiplayed(forgottenpasswordlink);
	}

	public boolean isRegisterLinkExist() {
		return elementUtil.doIsDiplayed(registerLink);
	}

	@Step("login with username: {0} and password {1}")
	public AccontsPage doLogin(String un, String pwd) {

		elementUtil.doSendKeys(emailId, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);

		return new AccontsPage(driver);
	}

	public RegisterPage navigateToRegisterPage() {
		elementUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}

}
