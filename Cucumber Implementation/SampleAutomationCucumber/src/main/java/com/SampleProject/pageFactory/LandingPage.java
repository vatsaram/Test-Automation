package com.SampleProject.pageFactory;

import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.SampleProject.testBase.TestBase;

public class LandingPage extends TestBase {

	public void setName(String name) {
		this.name = name;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String name, mobile, email;
	//public String name = "abd def";
	//public String mobile = "8983248390";
	//public String email = "abd@def.com";

	@FindBy(name = "full_name")
	WebElement fullName;

	@FindBy(name = "mobile")
	WebElement mobileNumber;

	@FindBy(name = "email")
	WebElement emailID;

	@FindBy(css = "button[type='submit']")
	WebElement submit;

	public LandingPage() {
		PageFactory.initElements(myDriver, this);
	}

	public DetailsPage submitLandingPage(Map<String, String> data) {
		fullName.sendKeys(data.get("name"));
		mobileNumber.sendKeys(data.get("mobile"));
		emailID.sendKeys(data.get("email"));
		submit.click();
		return new DetailsPage();
	}
}
