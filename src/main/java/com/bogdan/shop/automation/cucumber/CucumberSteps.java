package com.bogdan.shop.automation.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CucumberSteps {
	@Given("^I have added an item to my cart$")
	public void i_have_added_an_item_to_my_cart() throws Throwable {	    
	}
	
	@When("^I view the contents of my cart$")
	public void i_view_the_contents_of_my_cart() throws Throwable {	    
	}

	@Then("^I should see the contents of the cart include the item$")
	public void i_should_see_the_contents_of_the_cart_include_the_item() throws Throwable {	    
	}
}
