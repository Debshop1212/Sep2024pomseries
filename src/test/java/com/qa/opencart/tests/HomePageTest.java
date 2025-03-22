package com.qa.opencart.tests;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.basetest.BaseTest;
import com.qa.opencart.constant.AppConstants;
import com.qa.opencart.constant.AppError;
import com.qa.opencart.factory.DriverFactory;

public class HomePageTest extends BaseTest
{
	
@BeforeClass 	
public void homepagesetup()
{
	homePage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
}
	
	@Test
	public void EditAccountLinkExistTest()
	{
		
		Assert.assertTrue(homePage.isEditAccountLinkExist(),"===Edit Account link is not present===");
	}

	@Test
	public void homepagetitletest()
	{
		
		Assert.assertEquals(homePage.getHomePageTitle(),AppConstants.HOME_PAGE_TITLE,AppError.TITLE_NOT_FOUND_ERROR);
	}

	

	@Test
	public void homepageurltest()
	{
		
		Assert.assertTrue(homePage.getHomePageURL().contains(AppConstants.HOME_PAGE_URL_FRACTION),AppError.TITLE_NOT_FOUND_ERROR);
	}
	
	@Test
	public void headerstest()
	{
		
		List<String> actualHeaders=homePage.getheaderlist();
		System.out.println("Home Page header:=>"+actualHeaders);
		
	}
	
	
	@DataProvider
	public Object[][] getSearchData()
	{
	return new Object[][]
	{
		{"macbook",3},
		{"imac",1},
		{"samsung",2},
		{"canon",1},
		{"airtel",0}
		
	};
	}
	
	@Test(dataProvider="getSearchData")
	public void searchTest(String searchkey,int resultcount) throws InterruptedException
	{
		
		searchResultPage=homePage.dosearch(searchkey);
		Assert.assertEquals(searchResultPage.getProductResultsCount(), resultcount);
		Thread.sleep(3000);
		
		
	}
	
}

