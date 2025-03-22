package com.qa.opencart.basetest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {

	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected LoginPage loginPage;
	protected HomePage homePage;
	protected SearchResultsPage searchResultPage;
	protected ProductInfoPage productInfoPage;
	
	@BeforeTest
	public void setup()
	
	{
	df= new DriverFactory();	
	prop=df.initProp();
	driver= df.initDriver(prop);
	loginPage=new LoginPage(driver);
	
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
