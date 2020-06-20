package org.testframework.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(glue = "org/testframework/stepDefn", monochrome = true, plugin = {
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		"json:target/cucumber.json"},
		tags= {"@AllTest"},
		features = "classpath:FeatureFiles")

public class TestRunner {


}
