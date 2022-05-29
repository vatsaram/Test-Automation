package org.qa.GumTreeDemo.Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.qa.GumTreeDemo.Base.BasePage;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	@FindBy(id = "categoryId-wrp")
	public WebElement category;

	public String categorySelector = "//*[@id='categoryId-wrpwrapper']//*[contains(text(),'%s')]/ancestor::div[contains(@class,'j-selectbox__text')]";

	@FindBy(id = "search-query")
	public WebElement searchQuery;

	@FindBy(id = "search-area")
	public WebElement searchArea;

	@FindBy(id = "srch-radius-wrp")
	public WebElement searchRadius;

	public String radiusSelector = "//div[@id='srch-radius-wrpwrapper']//*[contains(text(),'%s')]/ancestor::div[contains(@class,'j-selectbox__text')]";

	@FindBy(xpath = "//button[@type='submit'")
	public WebElement submit;

	public void openHomePage()  {
		driver.get("https://www.gumtree.com.au/");
			
	}

}
