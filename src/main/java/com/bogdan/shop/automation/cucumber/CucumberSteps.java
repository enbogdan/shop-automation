package com.bogdan.shop.automation.cucumber;

import org.junit.Assert;

import com.bogdan.shop.automation.TestBase;
import com.bogdan.shop.automation.pageobjects.CartPage;
import com.bogdan.shop.automation.pageobjects.HomePage;
import com.bogdan.shop.automation.pageobjects.SearchResultsPage;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CucumberSteps extends TestBase {
	private HomePage home;
	private SearchResultsPage results;
	private CartPage cart;

	// a DI container should be used in order to inject pages and pass test data
	// between steps
	private String productCode;
	private int actualCartAmount;

	@Before
	public void beforeScenario() {
		setUp();
		setUpTest();
	}

	@After
	public void afterScenario() {
		tearDown();
	}

	@Given("^I have added an item to my cart$")
	public void i_have_added_an_item_to_my_cart() {
		home = new HomePage(getDriver());
		home.openPage();

		results = home.searchProduct("%");
		results.openFirstProductQuickView();
		productCode = results.getProductCode();
		results.addProductToCart();
	}

	@When("^I view the contents of my cart$")
	public void i_view_the_contents_of_my_cart() {
		cart = results.viewCart();
		actualCartAmount = cart.getItemCartAmount(productCode);
	}

	@Then("^I should see the contents of the cart include the item$")
	public void i_should_see_the_contents_of_the_cart_include_the_item() {
		Assert.assertEquals(1, actualCartAmount);
		cart.clearCart();
	}
}
