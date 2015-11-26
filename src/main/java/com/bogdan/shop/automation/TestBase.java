package com.bogdan.shop.automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {
	protected static final String appURL = "APP_URL";
	protected static final String browser = "BROWSER";
	protected static final String chromeDriver = "CHROME_DRIVER";

	private static Properties properties;
	private WebDriver driver;

	private static void loadProperties() {
		String propsPath = System.getProperty("test.config");
		if (propsPath == null)
			propsPath = "src/test/resources/properties/env-qa.properties"; // default

		try {
			FileInputStream fis = new FileInputStream(new File(propsPath));
			properties = new Properties();
			properties.load(fis);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeClass
	public static void setUp() {
		System.out.println("@BeforeClass - setup");
		loadProperties();
	}

	@Before
	public void setUpTest() {
		System.out.println("@BeforeTest");
		driver = initWebDriver(properties.getProperty(browser));
		driver.manage().window().maximize();
	}

	@After
	public void tearDown() throws InterruptedException {
		System.out.println("@AfterTest - tearDown");
		Thread.sleep(2000);
		driver.close();
	}

	public static String getAppUrl() {
		return properties.getProperty(appURL);
	}

	public WebDriver getDriver() {
		return driver;
	}

	private WebDriver initWebDriver(String... browserName) {
		switch (browserName[0]) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					properties.getProperty(chromeDriver));
			return new ChromeDriver();
		default:
		case "firefox":
			return new FirefoxDriver();
		}
	}
}
