package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.constant.AppConstants;
import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {
	
	
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String highlight;
	
	public static ThreadLocal<WebDriver> t1Driver=new ThreadLocal<WebDriver>();
	
	
	public WebDriver initDriver(Properties prop)

	{
		String browsername=prop.getProperty("browser");
		highlight=prop.getProperty("highlight");
		System.out.println("browser name is "+browsername);
		optionsManager=new OptionsManager(prop);
		
		switch(browsername.trim().toLowerCase())
		{
		case "chrome":
			t1Driver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		//driver=new ChromeDriver(optionsManager.getChromeOptions());
		break;
		case "firefox":
		t1Driver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		//driver=new FirefoxDriver(optionsManager.getFirefoxOptions());
		break;
		
		case "edge":
		t1Driver.set(new EdgeDriver(optionsManager.getEdgeOptions()));	
		//driver=new EdgeDriver(optionsManager.getEdgeOptions());
		break;
		
		case "safari":
		driver=new SafariDriver();
		break;
			
		default:
			System.out.println("Please pass the valid browser name"+browsername);
			throw new FrameworkException("===invalid browser name");
		
				
			
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
	}
	
	public static WebDriver getDriver()
	{
		return t1Driver.get();
	}
	

	//supply env name using maven command line
	//mvn clean install -Denv="qa"
	public Properties initProp()
		
	{
		String envName=System.getProperty("env");
		System.out.println("running test suite on env"+envName);
		FileInputStream ip=null;
		prop=new Properties();
		
		try
		{
		if(envName==null)
		{
			System.out.println("no env is passed,hence running the test suite on qa environment");
			
				ip=new FileInputStream(AppConstants.CONFIG_QA_PROP_FILE_PATH);
			 
			}
		
		
		else
		{
			switch(envName.trim().toLowerCase())
			{
			case "qa":
				
				ip=new FileInputStream(AppConstants.CONFIG_QA_PROP_FILE_PATH);
			    break;
				
			case "dev":
				
				ip=new FileInputStream(AppConstants.CONFIG_DEV_PROP_FILE_PATH);
				break;
				
			case "stage":
				
				ip=new FileInputStream(AppConstants.CONFIG_STAGE_PROP_FILE_PATH);
			    break;
				
			case "uat":
				
				ip=new FileInputStream(AppConstants.CONFIG_UAT_PROP_FILE_PATH);
				break;
				
			case "production":
				
			ip=new FileInputStream(AppConstants.CONFIG_PROD_PROP_FILE_PATH);
			break;
				default:
					throw new FrameworkException("===Invalid Env===");
					
					
				
			
		}
			}
	
		prop.load(ip);
		}
	catch(FileNotFoundException e)
	{
		e.printStackTrace();
	}
	
	catch(IOException e )
	{
		e.printStackTrace();
	}
	
		
	return prop;
	}}
