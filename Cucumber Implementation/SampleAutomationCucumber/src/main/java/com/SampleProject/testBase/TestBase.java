package com.SampleProject.testBase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.SampleProject.testUtil.TestUtility;


public class TestBase {
	
	public static Properties prop;
	public static WebDriver myDriver;
	
	public TestBase()
	{
		try 
		{
			prop = new Properties();
			InputStream file = TestBase.class.getResourceAsStream("/config.properties");
			prop.load(file);
			// load a properties file
			
			
		}
		
		catch (FileNotFoundException e) 
		{
			System.out.println("File not found");
			e.printStackTrace();
		}
		
		catch(IOException e) 
		{
			System.out.println("IO Exception");
			e.printStackTrace();
		}
		
		
	}
	
	public void setBrowser()
	{
		
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			ChromeOptions optns = new ChromeOptions();
			optns.addArguments("disable-infobars");
			myDriver = new ChromeDriver(optns);
			System.out.println("Browser set to Chrome");
		}
		
		myDriver.manage().window().maximize();
		myDriver.manage().deleteAllCookies();
		myDriver.manage().timeouts().pageLoadTimeout(TestUtility.DRIVER_TIME_OUT, TimeUnit.SECONDS);
		myDriver.manage().timeouts().implicitlyWait(TestUtility.DRIVER_IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		
		myDriver.get(prop.getProperty("url"));
	}

	
	public void closeBrowser() {
		myDriver.close();
		myDriver.quit();
	}

}
