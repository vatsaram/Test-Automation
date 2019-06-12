package com.SampleProject.pageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.SampleProject.testBase.TestBase;
import com.SampleProject.testUtil.TestUtility;

public class EligibilityPage extends TestBase {

	@FindBy(css = "strong[class='capacity']")
	WebElement eligibleAmount;

	@FindBy(css = "span[class='repayment-amount']")
	WebElement repaymentAmount;
	
	@FindBy(xpath = "//*[contains(text(), 'does not meet our current lending criteria.')]")
	WebElement ineligibleCust;

	public EligibilityPage() {
		PageFactory.initElements(myDriver, this);

	}

	public boolean checkEligibleAmount() {
		return eligibleAmount.isEnabled();

	}

	public boolean checkRepaymentAmount() {
		return repaymentAmount.isEnabled();
	}
	
	public boolean checkIneligibleCust() {
		return ineligibleCust.isDisplayed();
	}

}
