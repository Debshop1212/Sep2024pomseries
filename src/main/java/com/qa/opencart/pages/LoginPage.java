package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constant.AppConstants;
import com.qa.opencart.utill.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		eleutil=new ElementUtil(driver);
	}
	
	
	private By forgotPwdLink=By.linkText("Forgotten Password");
	private By emailid=By.id("input-email");
	private By password=By.id("input-password");
	private By submit=By.xpath("//input[@type='submit']");
	
	
	
	public String getLoginPageTitle()
	
	{
		String title=eleutil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_TIMEOUT);
		return title;
		
	}
	
	public String getLoginPageURL()
	{
		String url=eleutil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIMEOUT);
		return url;
	}

	
	public boolean isForgotPwdLinkExist()
	{
		return eleutil.doIsElementDisplayed(forgotPwdLink);
	}
	
	public HomePage doLogin(String username,String pwd)
	{
		eleutil.waitForElementVisible(emailid, AppConstants.DEFAULT_TIMEOUT).sendKeys(username);
		eleutil.doSendKeys(password, pwd);
		eleutil.doClick(submit);
		return new HomePage(driver);
	}
}
