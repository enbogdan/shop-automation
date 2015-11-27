package com.bogdan.shop.automation;

import org.junit.Assert;
import org.junit.Test;

import com.bogdan.shop.automation.TestBase;
import com.bogdan.shop.automation.pageobjects.CartPage;
import com.bogdan.shop.automation.pageobjects.HomePage;
import com.bogdan.shop.automation.pageobjects.SearchResultsPage;

public class WebDriverTest extends TestBase {

	// needs overall stability improvements with Firefox, using more explicit
	// waits, as some elements in page are dynamic
	@Test
	public void independantProductTest() {
		HomePage home = new HomePage(getDriver());
		home.openPage();
		SearchResultsPage results = home.searchProduct("%");

		// should be made more stable. mouseOver not always displays the
		// "add to cart" link. another element should be used for this
		results.openFirstProductQuickView();

		String productCode = results.getProductCode();
		results.addProductToCart();
		CartPage cart = results.viewCart();

		int actualCartAmount = cart.getItemCartAmount(productCode);
		Assert.assertEquals(1, actualCartAmount);

		cart.clearCart();
	}

	// for multi-threading, 3 tests with 2 threads
	@Test
	public void secondInstance() {
		HomePage home = new HomePage(getDriver());
		home.openPage();
		SearchResultsPage results = home.searchProduct("ASUS CP6130");
		results.openFirstProductQuickView();
		String productCode = results.getProductCode();
		results.addProductToCart();
		CartPage cart = results.viewCart();
		int actualCartAmount = cart.getItemCartAmount(productCode);
		Assert.assertEquals(1, actualCartAmount);
		cart.clearCart();
	}

	// for multi-threading, 3 tests with 2 threads
	@Test
	public void thirdInstance() {
		HomePage home = new HomePage(getDriver());
		home.openPage();
		SearchResultsPage results = home.searchProduct("D0152X65HT");
		results.openFirstProductQuickView();
		String productCode = results.getProductCode();
		results.addProductToCart();
		CartPage cart = results.viewCart();
		int actualCartAmount = cart.getItemCartAmount(productCode);
		Assert.assertEquals(1, actualCartAmount);
		cart.clearCart();
	}

}
