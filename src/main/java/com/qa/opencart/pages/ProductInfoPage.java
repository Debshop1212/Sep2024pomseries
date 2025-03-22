package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constant.AppConstants;
import com.qa.opencart.utill.ElementUtil;

public class ProductInfoPage {
	
private WebDriver driver;
private ElementUtil eleutil;
private Map<String,String> productMap;
	
	public ProductInfoPage(WebDriver driver)
	{
		this.driver=driver;
		eleutil=new ElementUtil(driver);
	}

	private By productHeader=By.tagName("h1");
	private By productImages=By.cssSelector("ul.thumbnails img");
	private By productMetaData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	public String getProductHeader() throws InterruptedException
	{
		
		String header=eleutil.doElementGetText(productHeader);
		System.out.println("Product Header=>"+ header);
		return header;
	}
	
	
	public int getProductImagesCount() throws InterruptedException
	{
		
		int imagesCount=eleutil.waitForElementsPresence(productImages,AppConstants.SHORT_TIMEOUT).size();
		System.out.println(getProductHeader()+ " Image Count is ===> "+ imagesCount);
		return imagesCount;
	}
	
	
	public Map<String,String> getProductInfo() throws InterruptedException 
	{
		//productMap= new HashMap<String,String>();
		//productMap= new LinkedHashMap<String,String>();
		productMap= new TreeMap<String,String>();
		productMap.put("header", getProductHeader());
		productMap.put("imagescount",getProductImagesCount()+"");
		getProductMetaData();
		getProductPriceData();
		
		return productMap;
		
	}
	
	
	private void getProductMetaData()
	{

		List<WebElement> metalist= eleutil.waitForElementsPresence(productMetaData, AppConstants.DEFAULT_TIMEOUT);
    
	  for(WebElement e : metalist)
	  {
		  String metatext=e.getText();
		  String meta[]=metatext.split(":");
		  String metaKey=meta[0].trim();
		  String metaValue=meta[1].trim();
		  
		  productMap.put(metaKey, metaValue);
		  
	  }
	}
	
	
	private void getProductPriceData()
	{
		List<WebElement> pricelist= eleutil.waitForElementsPresence(productPriceData, AppConstants.DEFAULT_TIMEOUT);	
		String productPrice=pricelist.get(0).getText().trim();
		String productExTax=pricelist.get(1).getText().split(":")[1].trim();
		productMap.put("price",productPrice);
		productMap.put("ExTax",productExTax);
		
	}
	
	}
	



