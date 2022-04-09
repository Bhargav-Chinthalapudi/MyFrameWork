package com.qa.opencart.test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Excelutil;

public class RegisterTest extends BaseTest {

	@BeforeClass
	public void regPageSetup() {
		register = loginpage.navigateToRegisterPage();
	}

	public static String getRandomEmail() {
		Random random = new Random();
		String email = "myautomationmail" + random.nextInt(1000) + "@gmail.com";
		return email;
	}

	@DataProvider
	public Object[][] getRegisterData() {
		Object data[][] = Excelutil.getTestData(Constants.REGISTER_SHEET_NAME);
		return data;
	}

	@Test(dataProvider = "getRegisterData")
	public void registerTest(String firstname, String lastname, String telephone, String password, String subscribe) {

		Assert.assertTrue(register.registration(firstname, lastname, getRandomEmail(), telephone, password, subscribe));
	}

}
