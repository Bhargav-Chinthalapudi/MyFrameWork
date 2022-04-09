package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100 :open cart application - Design login page")
@Story("US 101 : Login page features with some basic modules and functionalities")
public class LoginPageTest extends BaseTest {

	@Description("login page title test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void loginPageTitleTest() {
		String title = loginpage.getLoginPageTitle();
		System.out.println("Actual page title is : " + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);

	}

	@Test
	public void loginPageUrlTest() {
		String url = loginpage.getLoginPageUrl();
		System.out.println("Account page url is :" + url);
		Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_URL));
	}

	@Test
	public void forgotPasswordLinkTest() {
		Assert.assertTrue(loginpage.isForgotPasswordExist());

	}

	@Test
	public void registerLinkTest() {
		Assert.assertTrue(loginpage.isRegisterLinkExist());

	}

	@Test
	public void loginTest() {
		loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());

	}

}
