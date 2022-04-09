package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import com.qa.opencart.utils.Constants;

public class RegistrationTest extends BaseTest {

	@Test
	public void registerLinkExistTest() {

		Assert.assertTrue(regpage.isRegisterLinkExist());
	}

	@Test
	public void clickRegisterLinkTest() {
		String registrationtitle = regpage.doClickRegisterLink();
		Assert.assertEquals(registrationtitle, Constants.REGISTER_PAGE_TITLE);
	}

}
