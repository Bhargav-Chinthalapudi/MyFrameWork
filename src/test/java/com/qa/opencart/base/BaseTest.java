package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccontsPage;
import com.qa.opencart.pages.Cart;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.ResultsPage;

public class BaseTest {
	public WebDriver driver;
	public DriverFactory df;
	public LoginPage loginpage;
	public Properties prop;
	public AccontsPage accPage;
	public RegistrationPage regpage;
	public ResultsPage resultpage;
	public ProductInfoPage prodInfo;
	public Cart cart;
	public RegisterPage register;
	public SoftAssert softAssert;
	

	@BeforeTest
	public void setUp() {
		df = new DriverFactory();
		prop=df.initProp();
		driver = df.initDriver(prop);
		loginpage = new LoginPage(driver);
		regpage=new RegistrationPage(driver);
		softAssert=new SoftAssert();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
