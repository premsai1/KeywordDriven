package com.qa.AppAutomation.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
/**
 * @author PremSai Manthani
 * @purpose this class is the base class for launching the browser 
 */
public class TestBase {

	public WebDriver driver;
	public Properties prop;

	public WebDriver init_driver(String browserName) {
		if (browserName.contentEquals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"/home/user/Desktop/selenium/drivers/chromedriver_linux64/chromedriver");

			if (prop.getProperty("headless").contentEquals("yes")) {
				ChromeOptions option = new ChromeOptions();
				option.addArguments("--headless");
				driver = new ChromeDriver(option);
			} else {
				driver = new ChromeDriver();
			}
		}
		return driver;
	}

	public Properties init_properties() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(
					"/home/user/home/Prem/KeyWord_AppAutomation/src/main/java/com/qa/AppAutomation/Config/Config.properties");
			prop.load(ip);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}
