package org.qa.GumTreeDemo.StepDefn;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.qa.GumTreeDemo.Base.Page;
import org.qa.GumTreeDemo.Pages.HomePage;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class UIStepDefn {
	
	protected static WebDriver driver;
	// Driver wait object which all class would use
	protected WebDriverWait wait;
	protected Page page;

	@BeforeAll
	public static void setup() {
		// Create a Chrome driver. Current implementation is set to only to Chrome
		WebDriverManager.chromedriver().setup();
	}
	
	@AfterAll
	public static void teardown() {
		driver.quit();
		if(driver != null)
			driver = null;
//   Setting the driver variable to null
	}
	
	@Given("I initialise the drivers and open Gum Tree home page")
	public void initialiseAndOpenHomePage() {
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(15), Duration.ofMillis(100));
		driver.manage().window().maximize();
		page = new Page(driver, wait);
		page.getInstance(HomePage.class).openHomePage();

	}


	@When("I search for Category - {string}")
	public void searchForCategory(String string) {
	    throw new io.cucumber.java.PendingException();
	}

	@When("search for the item - {string}")
	public void searchForItem(String string) {
	    throw new io.cucumber.java.PendingException();
	}

	@When("set the region as {string}")
	public void setRegionAs(String string) {
	    throw new io.cucumber.java.PendingException();
	}

	@When("range as {string}")
	public void setRangeAs(String string) {
	    throw new io.cucumber.java.PendingException();
	}


	@Then("click on search button to check the results")
	public void clickOnSearchResults() {
	    throw new io.cucumber.java.PendingException();
	}


	@Given("search results are displayed on the web page from previous tests")
	public void verifyPreviousScenarioSuccessful() {
	    throw new io.cucumber.java.PendingException();
	}


	@Then("open a random result from the search results")
	public void open_a_random_result_from_the_search_results() {
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Verify the ad details page opens")
	public void verify_the_ad_details_page_opens() {
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Verify a numeric ad id is displayed in the breadcrumbs")
	public void verify_a_numeric_ad_id_is_displayed_in_the_breadcrumbs() {
	    throw new io.cucumber.java.PendingException();
	}


	@Then("Verify atleast one similar ad is displayed in the page")
	public void verify_atleast_one_similar_ad_is_displayed_in_the_page() {
	    throw new io.cucumber.java.PendingException();
	}


}
