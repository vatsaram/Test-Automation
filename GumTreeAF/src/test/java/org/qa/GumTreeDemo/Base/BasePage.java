package org.qa.GumTreeDemo.Base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage extends Page {
	
	public BasePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
	
    //Click Method
    public void click (WebElement webElement) {
			webElement.click();
    }
    
    //Write Text
    public void writeText (WebElement webElement, String text) {
//			wait.until(ExpectedConditions.elementToBeClickable(webElement));
			webElement.sendKeys(text);
    }
    //Read Text
    public String readTextBy (WebElement webElement) {
//			wait.until(ExpectedConditions.elementToBeSelected(webElement));
		return webElement.getText();
    }
    
    public List<WebElement> returnListOfWebElement (By element) {
    	return driver.findElements(element);
    }
    
    public WebElement getWebElement(By element) {
    	return driver.findElement(element);
    	
    }

}
