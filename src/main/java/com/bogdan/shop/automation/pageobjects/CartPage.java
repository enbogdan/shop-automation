package com.bogdan.shop.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

	private WebDriver driver;
	
	private String itemAmountPrefix = "//table[.//span[text()='";
	private String itemAmountSuffix = "']]//input[contains(@id,'amount')]";
	private By clearCartButton = By.linkText("Clear cart");

	public CartPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public int getItemCartAmount(String productCode) {
		String amountValue = driver.findElement(By.xpath(itemAmountPrefix+productCode+itemAmountSuffix)).getAttribute("value");
		return Integer.valueOf(amountValue);
	}
	
	public void clearCart() {
		driver.findElement(clearCartButton).click();
	}

}
