package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {

	private ElementUtil eleutil;

	private By firstName = By.cssSelector("input#input-firstname");
	private By lastName = By.cssSelector("input#input-lastname");
	private By eMail = By.cssSelector("input#input-email");
	private By telephone = By.cssSelector("input#input-telephone");
	private By password = By.cssSelector("input#input-password");
	private By passwordConfirm = By.cssSelector("input#input-confirm");
	private By subscirberYes = By.xpath("(//label[@class='radio-inline']/input)[position()=1]");
	private By subscirberNo = By.xpath("(//label[@class='radio-inline']/input)[position()=2]");
	private By agreeCheckbox = By.cssSelector("div.buttons input");
	private By continueButton = By.xpath("//div[@class='buttons']//input[@value='Continue']");
	private By successMsg = By.cssSelector("div#content h1");
	private By logout = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegisterPage(WebDriver driver) {

		eleutil = new ElementUtil(driver);
	}

	public boolean registration(String fistName, String lastName, String email, String telephone, String password,
			String suscribe) {
		fillRegistrationForm(fistName, lastName, email, telephone, password);
		selectSubscriptionOption(suscribe);
		selectAgreementAndContinue();
		return registrationStatus();

	}

	private void fillRegistrationForm(String fistName, String lastName, String email, String telephone,
			String password) {

		eleutil.doSendKeys(firstName, fistName);
		eleutil.doSendKeys(this.lastName, lastName);
		eleutil.doSendKeys(eMail, email);
		eleutil.doSendKeys(this.telephone, telephone);
		eleutil.doSendKeys(this.password, password);
		eleutil.doSendKeys(this.passwordConfirm, password);

	}

	private void selectSubscriptionOption(String suscribe) {
		if (suscribe.equalsIgnoreCase("yes")) {
			eleutil.doClick(subscirberYes);
		} else
			eleutil.doClick(subscirberNo);
	}

	private void selectAgreementAndContinue() {
		eleutil.doClick(agreeCheckbox);
		eleutil.doClick(continueButton);
	}

	private boolean registrationStatus() {
		String msg = eleutil.doGetText(successMsg);

		if (msg.contains(Constants.REGISTER_SUCCESS_MSG)) {
			eleutil.doClick(logout);
			eleutil.doClick(registerLink);
			return true;
		}
		eleutil.doClick(agreeCheckbox);
		return false;
	}

}
