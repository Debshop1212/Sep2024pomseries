package com.qa.opencart.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.opencart.basetest.BaseTest;
import com.qa.opencart.constant.AppConstants;
import com.qa.opencart.constant.AppError;
import com.qa.opencart.factory.DriverFactory;

public class LoginPageTest extends BaseTest{
	@Test
	public void LoginTitleTest()
	{
		String actTitle=loginPage.getLoginPageTitle();
		
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE,AppError.TITLE_NOT_FOUND_ERROR);
	}
	@Test
	public void LoginPageURLTest()
	{
		String actURL=loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION),AppError.URL_NOT_FOUND_ERROR);
	}
	
	@Test
	public void forgotPwdLinkExistTest()
	{
		
		Assert.assertTrue(loginPage.isForgotPwdLinkExist(),AppError.ELEMENT_NOT_FOUND_ERROR);
	}
	
	@Test(priority=Integer.MAX_VALUE)
	
	public void loginTest() throws InterruptedException
	{
		
		
		homePage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Thread.sleep(1000);
		Assert.assertEquals(homePage.getHomePageTitle(), AppConstants.HOME_PAGE_TITLE,AppError.ELEMENT_NOT_FOUND_ERROR);
	}
}
