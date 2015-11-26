package com.bogdan.shop.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {
	private WebDriver driver;

	private By firstProduct = By.cssSelector(".ty-column3:first-child");
	private By firstQuickViewLink = By.xpath("(//a[text()='Quick view'])[1]");
	private By addToCartButton = By.xpath("//button[text()='Add to cart']");
	private By productCodeText = By.cssSelector("span[id*='product_code']");
	private By viewCartLink = By.linkText("View cart");

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
	}

	// todo: use wrapper on interacting with element, so we can add explicit
	// waits and reduce cod duplication
	public void openFirstProductQuickView() {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(firstProduct)).perform();
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.visibilityOfElementLocated(firstQuickViewLink));
		driver.findElement(firstQuickViewLink).click();
	}

	public String getProductCode() {
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.visibilityOfElementLocated(productCodeText));
		return driver.findElement(productCodeText).getText();
	}

	public void addProductToCart() {
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.visibilityOfElementLocated(addToCartButton));
		driver.findElement(addToCartButton).click();
	}

	public CartPage viewCart() {
		new WebDriverWait(driver, 10).until(ExpectedConditions
				.visibilityOfElementLocated(viewCartLink));
		driver.findElement(viewCartLink).click();
		return new CartPage(driver);
	}

}
