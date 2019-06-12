package com.SampleProject.runner;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"classpath:features"},
glue = {"com.SampleProject.stepDefn" }, 
format = { "pretty", "html:test_output", "json:json_output/cucumber.json",
				"junit:junit_output/cucumber.xml" }, 
monochrome = true, 
dryRun = false)


public class ApplnRunnerIT {
	
	public static void main(String[] args) {
		JUnitCore.main(ApplnRunnerIT.class.getName());
	}
}
