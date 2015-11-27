package com.bogdan.shop.automation.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(glue = "com.bogdan.shop.automation.cucumber", features = { "src/test/resources/features" }, plugin = {
		"pretty", "json:target/cucumber.json" })
public class CucumberTest {

}
