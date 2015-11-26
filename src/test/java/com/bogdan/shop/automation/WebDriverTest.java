package com.bogdan.shop.automation;

import org.junit.Assert;
import org.junit.Test;

import com.bogdan.shop.automation.TestBase;
import com.bogdan.shop.automation.pageobjects.CartPage;
import com.bogdan.shop.automation.pageobjects.HomePage;
import com.bogdan.shop.automation.pageobjects.SearchResultsPage;

public class WebDriverTest extends TestBase {
	
	@Test
	public void test() {
		HomePage home = new HomePage(getDriver());
		home.openPage();
		SearchResultsPage results = home.searchProduct("%");
		
		results.openFirstProductQuickView();
		String productCode = results.getProductCode();
		results.addProductToCart();
		CartPage cart = results.viewCart();
		
		int actualCartAmount = cart.getItemCartAmount(productCode);
		Assert.assertEquals(1, actualCartAmount);
		
		cart.clearCart();
	}

}
