package com.bogdan.shop.automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {
	private static final String appURL = "APP_URL";
	private static final String browser = "BROWSER";
	private static final String chromeDriver = "CHROME_DRIVER";
	private static final String implicitWait = "SELENIUM_IMPLICIT_WAIT";

	private static Properties properties;
	private WebDriver driver;

	// properties should be synchronized, made thread safe. one thread setup
	// could write properties, other thread could read properties
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

	// should be improved to support multiple browsers in the same execution
	@Before
	public void setUpTest() {
		System.out.println("@BeforeTest");
		int implicitWaitSeconds = Integer.parseInt(properties
				.getProperty(implicitWait));
		driver = initWebDriver(properties.getProperty(browser));
		driver.manage().timeouts()
				.implicitlyWait(implicitWaitSeconds, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@After
	public void tearDown() {
		System.out.println("@AfterTest - tearDown");
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
