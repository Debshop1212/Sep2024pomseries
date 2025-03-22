package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.basetest.BaseTest;
import com.qa.opencart.utill.ExcelUtil;

public class ProductInfoPageTest extends BaseTest

{
	@BeforeClass
	public void productInfoSetup()
	{
		homePage=loginPage.doLogin("septbatch2024@open.com", "Selenium@12345");
	}
	
	@DataProvider
	public Object[][] getProductData()
	{
		return new Object[][]
				{
			{"macbook","MacBook Pro"},
			{"macbook","MacBook Air"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"}
					
				
			};
					
			
				}
	@Test(dataProvider="getProductData")
	public void productSearchHeaderTest(String searchkey,String productName) throws InterruptedException
	{
		searchResultPage=homePage.dosearch(searchkey);
		Thread.sleep(2000);
		productInfoPage=searchResultPage.selectProduct(productName);
		String actualProductHeader=productInfoPage.getProductHeader();
		Assert.assertEquals(actualProductHeader, productName);
	}
	
	
	@DataProvider
	
	public Object[][] getProductImageData()
	{
		return new Object[][]
				{
			{"macbook","MacBook Pro",4},
			{"macbook","MacBook Air",4},
			{"imac","iMac",3},
			{"samsung","Samsung SyncMaster 941BW",1}
					
				
			};
					
			
				}
	
@DataProvider
	
	public Object[][] getProductImageSheetData()
	{
		Object productData[][]=ExcelUtil.getTestData("product");
		return productData;
				
	}
	
	
	@Test(dataProvider="getProductImageSheetData")
	public void productImagesCountTest(String searchkey,String productName,String expectedImagesCount) throws InterruptedException
	{
		searchResultPage=homePage.dosearch(searchkey);
		Thread.sleep(2000);
		productInfoPage=searchResultPage.selectProduct(productName);
		int actualProductImagesCount= productInfoPage.getProductImagesCount();
		Assert.assertEquals(actualProductImagesCount, Integer.parseInt(expectedImagesCount));
	}
	
	
	
	@Test
	public void productInfoTest() throws InterruptedException
	{
		searchResultPage=homePage.dosearch("macbook");
		
		productInfoPage=searchResultPage.selectProduct("MacBook Pro");
		
		Map<String,String> productInfoMap=productInfoPage.getProductInfo();
		productInfoMap.forEach((k,v)-> System.out.println(k+":"+v));
		
		SoftAssert softassert=new SoftAssert();
		softassert.assertEquals(productInfoMap.get("header"), "MacBook Pro");
		softassert.assertEquals(productInfoMap.get("Brand"), "Apple");
		softassert.assertEquals(productInfoMap.get("Availability"), "In Stock");
		softassert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
		softassert.assertEquals(productInfoMap.get("Reward Points"), "800");
		softassert.assertEquals(productInfoMap.get("price"), "$2,000.00");
		softassert.assertEquals(productInfoMap.get("ExTax"), "$2,000.00");
		softassert.assertAll();
		
	}
	

}
