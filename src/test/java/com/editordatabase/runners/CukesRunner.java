package com.editordatabase.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(

		plugin = "html:target/cucumber-reports", 
		features = "./src/test/resources/com/editordatabase/features", 
		glue = "com/editordatabase/step_definitions", 
		tags = "@JDBCEDITOR", 
		dryRun = false)

public class CukesRunner {

}
