package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constant.AppConstants;
import com.qa.opencart.utill.ElementUtil;



public class HomePage {
	
	private WebDriver driver;
	private ElementUtil eleutil;
	
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		eleutil=new ElementUtil(driver);
	}
	
	
	private By editAccountLink=By.linkText("Edit Account");
	private By headers=By.cssSelector("div#content > h2");
	private By search=By.name("search");
	private By searchIcon=By.cssSelector("div#search button");

	
	public boolean isEditAccountLinkExist()
	{
		return driver.findElement(editAccountLink).isDisplayed();
	}
	
public String getHomePageTitle()
	
	{
		String title=eleutil.waitForTitleIs(AppConstants.HOME_PAGE_TITLE, AppConstants.DEFAULT_TIMEOUT);
		return title;
		
	}
	
	public String getHomePageURL()
	{
		String url=eleutil.waitForURLContains(AppConstants.HOME_PAGE_URL_FRACTION, AppConstants.DEFAULT_TIMEOUT);
		return url;
	}
	public List<String> getheaderlist()
	{
		List<WebElement> headerlist=eleutil.waitForElementsVisible(headers, AppConstants.SHORT_TIMEOUT);
		List<String> headersValList=new ArrayList<String>();
		for (WebElement e: headerlist)
		{
			String text=e.getText();
			headersValList.add(text);
		}
		return headersValList;
	}
	
	
	
	public SearchResultsPage dosearch(String searchkey)
	
	{
		System.out.println("search key"+searchkey);
		WebElement searchele=eleutil.waitForElementVisible(search,AppConstants.DEFAULT_TIMEOUT );
		searchele.clear();
		searchele.sendKeys(searchkey);
		driver.findElement(searchIcon).click();
		//eleutil.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}
}
