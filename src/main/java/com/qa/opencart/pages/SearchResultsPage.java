package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constant.AppConstants;
import com.qa.opencart.utill.ElementUtil;

public class SearchResultsPage {
	
private WebDriver driver;
private ElementUtil eleutil;
	
	public SearchResultsPage(WebDriver driver)
	{
		this.driver=driver;
		eleutil=new ElementUtil(driver);
	}

	
	
	private By productResults=By.cssSelector("div.product-thumb");
	
	public int getProductResultsCount()
	
	{
		//int resultcount=eleutil.waitForElementsVisible(productResults, AppConstants.DEFAULT_TIMEOUT).size();
		
		int resultcount=eleutil.waitForElementsPresence(productResults, AppConstants.DEFAULT_TIMEOUT).size();
		//int resultcount=searchResultPage.getProductResultsCount();
		System.out.println("Products Result Count"+resultcount);
		return resultcount;
	}
	
	
	public ProductInfoPage selectProduct(String productname) throws InterruptedException
	
	{
		System.out.println("product name"+ productname);
		Thread.sleep(2000);
		
		driver.findElement(By.linkText(productname)).click();
		
	
		//eleutil.doClick(By.linkText(productname));
		return new ProductInfoPage(driver);
	}
}
