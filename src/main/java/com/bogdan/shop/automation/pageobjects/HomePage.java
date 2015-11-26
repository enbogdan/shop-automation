package com.bogdan.shop.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.bogdan.shop.automation.TestBase;

public class HomePage {
	private WebDriver driver;
	
	private By searchInput = By.name("hint_q");
	private By searchButton = By.cssSelector(".top-search button");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void openPage() {
		driver.get(TestBase.getAppUrl());
	}
	
	public SearchResultsPage searchProduct(String productName) {
		driver.findElement(searchInput).sendKeys(productName);
		driver.findElement(searchButton).click();
		return new SearchResultsPage(driver);
	}
}
