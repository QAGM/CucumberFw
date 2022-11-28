package com.amazon.runner;

import org.junit.runner.RunWith;		
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)				
@io.cucumber.junit.CucumberOptions(features="src\\test\\java\\testrkgrp\\testrkaid\\feature",
		glue={"testrkgrp.testrkaid.steps","testrkgrp.testrkaid.common","testrkgrp.testrkaid.util"},
		tags="@verify",
		plugin = {
		"pretty",
		"html:target/cucumber-reports/cucumber-pretty.html",
		"json:target/cucumber-reports/CucumberTestReport.json",
		"rerun:target/cucumber-reports/rerun.txt"
		},publish = true
		)						
public class TestRunner 				
{		

}
