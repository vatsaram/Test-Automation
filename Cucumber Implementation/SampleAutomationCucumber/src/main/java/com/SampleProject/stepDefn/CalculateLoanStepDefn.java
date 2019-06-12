package com.SampleProject.stepDefn;

import java.util.Map;

import com.SampleProject.pageFactory.DetailsPage;
import com.SampleProject.pageFactory.EligibilityPage;
import com.SampleProject.pageFactory.LandingPage;
import com.SampleProject.testBase.TestBase;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import junit.framework.Assert;

public class CalculateLoanStepDefn extends TestBase {

	public CalculateLoanStepDefn() {
		super();
	}

	private LandingPage landingPageTest;
	private DetailsPage detailsPageTest;
	private EligibilityPage eligiblePageTest;

	@Before
	public void setup() {
		setBrowser();
		landingPageTest = new LandingPage();
	}

	@Given("^The user has provided the inputs$")
	public void readAllInputs(DataTable userData) {
	try {
			for(Map<String, String> data : userData.asMaps(String.class,String.class)) {
				
				detailsPageTest = landingPageTest.submitLandingPage(data);
				
				detailsPageTest.setDetailsPage(data);
				
				eligiblePageTest = detailsPageTest.submitDetailsPage();
				
			}
			
		} catch (Exception e) {
			System.out.println("Please check the data which you have provided");
			e.printStackTrace();
		}
		// }

	}

	

	/**
	 * @param data
	 */

	@SuppressWarnings("deprecation")
	@Then("^Assert the amount user is eligible for is displayed$")
	public void validAmtDisplayed() {
		Assert.assertTrue(eligiblePageTest.checkEligibleAmount());
	}

	@SuppressWarnings("deprecation")
	@Then("^Assert maximum repayment amount is displayed to the user$")
	public void validRepayAmtDisplayed() {
		Assert.assertTrue(eligiblePageTest.checkRepaymentAmount());
	}

	@SuppressWarnings("deprecation")
	@Then("^Assert that application provides a notice that user is not eligible for loan$")
	public void userIneligible() {
		Assert.assertTrue(eligiblePageTest.checkIneligibleCust());
	}
	
	@After()
	public void tearDown() {
		closeBrowser();
	}
}
