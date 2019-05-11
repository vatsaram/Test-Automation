package com.brightE.pageFactory;

import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.brightE.testBase.TestBase;
import com.brightE.testUtil.TestUtility;

public class DetailsPage extends TestBase {

	public String baseIncome, partnerIncome, otherIncome, mortgage, otherLoans, creditCard, livingCost;

	public void setBaseIncome(String baseIncome) {
		this.baseIncome = baseIncome;
	}

	public void setPartnerIncome(String partnerIncome) {
		this.partnerIncome = partnerIncome;
	}

	public void setOtherIncome(String otherIncome) {
		this.otherIncome = otherIncome;
	}

	public void setMortgage(String mortgage) {
		this.mortgage = mortgage;
	}

	public void setOtherLoans(String otherLoans) {
		this.otherLoans = otherLoans;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public void setLivingCost(String livingCost) {
		this.livingCost = livingCost;
	}

	public String maritalOption = TestUtility.maritalMarried;
	public String DependantOption = TestUtility.twoDepend;
	public String baseIncomeFreq = TestUtility.periodFortnight;
	public String partnerIncomeFreq = TestUtility.periodFortnight;
	public String otherIncomeFreq = TestUtility.periodFortnight;
	public String mortgageFreq = TestUtility.periodFortnight;
	public String otherLoansFreq = TestUtility.periodFortnight;
	public String livingCostFreq = TestUtility.periodFortnight;

	@FindBy(id = "base-net-income-amount")
	WebElement baseNetIncome;

	@FindBy(id = "partners-base-net-income-amount")
	WebElement partnerBaseIncome;

	@FindBy(id = "other-taxable-net-income-amount")
	WebElement otherTaxableIncome;

	@FindBy(id = "mortgage-repayment-amount")
	WebElement mortgageRepayment;

	@FindBy(id = "other-loan-commitments-amount")
	WebElement otherLoanAmount;

	@FindBy(id = "household-credit-card-limit-amount")
	WebElement houseCreditCard;

	@FindBy(id = "household-living-costs-amount")
	WebElement houseLivingCost;

	@FindBy(xpath = "//*[@id='pre-approval-how-much-can-i-borrow-form']//*[contains(@class,'input-field select')]//div[@class='select-wrapper']//input[@class='select-dropdown']")
	WebElement maritalStatus;

	@FindBy(xpath = "//*[@id='pre-approval-how-much-can-i-borrow-form']//*[contains(@class,'input-field select')][2]//div[@class='select-wrapper']//input[@class='select-dropdown']")
	WebElement dependents;

	@FindBy(css = "button[type='submit']")
	WebElement submit;

	@FindBy(css = "div[class='alert alert-dismissible fade in alert-danger']")
	WebElement missingInfo;

	/*
	 * Initialise page factory
	 * 
	 */

	public DetailsPage() {

		PageFactory.initElements(myDriver, this);
	}

	public void setDetailsPage(Map<String, String> data) {
		this.baseIncome = data.get("baseIncome");
		this.partnerIncome = data.get("partnerIncome");
		this.otherIncome = data.get("otherIncome");
		this.mortgage = data.get("mortgage");
		this.otherLoans = data.get("otherLoans");
		this.creditCard = data.get("creditCard");
		this.livingCost = data.get("livingCost");
	}

	/*
	 * Method for setting the details for the page
	 * 
	 */
	public EligibilityPage submitDetailsPage() {

		setMaritalDepend(maritalStatus, maritalOption);
		setMaritalDepend(dependents, DependantOption);
		enterInputParameters(baseNetIncome, baseIncome, baseIncomeFreq);
		enterInputParameters(partnerBaseIncome, partnerIncome, partnerIncomeFreq);
		enterInputParameters(otherTaxableIncome, otherIncome, otherIncomeFreq);
		enterInputParameters(mortgageRepayment, mortgage, mortgageFreq);
		enterInputParameters(otherLoanAmount, otherLoans, otherLoansFreq);
		houseCreditCard.sendKeys(creditCard);
		enterInputParameters(houseLivingCost, livingCost, livingCostFreq);
		submit.click();
		return new EligibilityPage();

	}


	/*
	 * Method for setting marital info and dependent details.
	 * 
	 */
	private void setMaritalDepend(WebElement myElement, String selection) {
		((JavascriptExecutor) myDriver).executeScript("arguments[0].scrollIntoView(true);", myElement);
		((JavascriptExecutor) myDriver).executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 500);");
		
		Actions actions = new Actions(myDriver);
		if (selection.equals(TestUtility.maritalSingle) || selection.equals(TestUtility.twoDepend)) {
			actions.moveToElement(myElement).click().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
					.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
		}

		else if (selection.equals(TestUtility.maritalMarried) || selection.equals(TestUtility.oneDepend)) {
			actions.moveToElement(myElement).click().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
					.sendKeys(Keys.ENTER).build().perform();
		} else if (selection.equals(TestUtility.maritalDefacto) || selection.equals(TestUtility.zeroDepend)) {
			actions.moveToElement(myElement).click().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();

		} else if (selection.equals(TestUtility.threeDepend)) {
			actions.moveToElement(myElement).click().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
					.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();

		}

	}

	/*
	 * Method for entering income & expense details
	 * 
	 */
	private void enterInputParameters(WebElement myElement, String value, String selection) {
		((JavascriptExecutor) myDriver).executeScript("arguments[0].scrollIntoView(true);", myElement);
		((JavascriptExecutor) myDriver).executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 500);");
		// included wait for viewing the changes entered

		myElement.sendKeys(value);
		Actions actions = new Actions(myDriver);
		if (selection.equals(TestUtility.periodMonth)) {
			actions.moveToElement(myElement).sendKeys(Keys.TAB).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
					.sendKeys(Keys.ENTER).build().perform();
		}

		else if (selection.equals(TestUtility.periodFortnight)) {
			actions.moveToElement(myElement).sendKeys(Keys.TAB).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build()
					.perform();
		} else {
			actions.moveToElement(myElement).sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();

		}
	}

}
